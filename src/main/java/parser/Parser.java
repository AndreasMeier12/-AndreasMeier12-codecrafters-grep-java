package parser;

import matcher.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    String regex;

    public Parser() {
            }


    public MatcherExpression parse(String regex


    ) {

        boolean startAnchor = regex.startsWith("^");
        boolean endAnchor = parseEndsWith(regex);

        String regexNew = regex;
        if (startAnchor){
            regexNew = regexNew.substring(1);
        }
        if (endAnchor){
            int length = regexNew.length();
            regexNew = regexNew.substring(0, length -1);

        }
        List<IMatcher> matchers = parseMatcher(regexNew);


        return new MatcherExpression(matchers, startAnchor, endAnchor);


    }


    private boolean parseEndsWith(String a){
        if (a.endsWith("\\\\$")){
            return true;
        }
        if (a.endsWith("\\$")){
            return false;
        }
        if (a.endsWith("$")){
            return true;
        }
        return false;


    }

    private List<IMatcher> parseMatcher(String regex) {
        List<IMatcher> res = new ArrayList<>();

        int pos = 0;
        while (pos < regex.length()) {
            int lookAhead = lookAhead(regex, pos);
            String subString = regex.substring(pos, pos + lookAhead);
            GroupParseResult groupParseResult = parseMatcherSingle(subString);
            pos = pos + groupParseResult.size;
            res.add(groupParseResult.matcher);
        }
        return res;
    }

    private GroupParseResult parseMatcherSingle(String subString) {
        if (subString.startsWith("[")){
            return parseGroup(subString);
        }

        Quantifier quantifier = getQuantifier(subString);

        switch (subString) {
            case "\\d":
                return new GroupParseResult(2, new DigitMatcher(quantifier));

            case "\\w":
                return new GroupParseResult(2, new AlphaNumericMatcher(quantifier));

            default:
                if (subString.length() > 1) {
                    throw new RuntimeException("Character \"" + subString + "\" not implemented");
                }
                return new GroupParseResult(1, new LiteralMatcher(subString.charAt(0), quantifier));

        }
    }

    private Quantifier getQuantifier(String a){
        if (a.endsWith("\\\\+")){
            return new Quantifier(1, Integer.MAX_VALUE);
        }
        if (a.endsWith("\\+")){
            return new Quantifier(1,1);
        }
        if (a.endsWith("+")){
            return new Quantifier(1, Integer.MAX_VALUE);
        }
        if (a.endsWith("\\\\?")){
            return new Quantifier(0, 1);
        }
        if (a.endsWith("\\?")){
            return new Quantifier(1,1);
        }
        if (a.endsWith("?")){
            return new Quantifier(0, 1);
        }



        return new Quantifier(1,1);


    }

    private int lookAhead(String regex, int pos) {
        int lookAhead = 0;
        boolean group = false;

        while (regex.charAt(pos + lookAhead) == '\\') {
            lookAhead++;
        }
        if (regex.charAt(pos + lookAhead) == '[') {
            lookAhead = lookAheadGroup(regex, pos);
            group = true;
        }

        if (lookAhead < regex.length() - 1 && (regex.charAt(lookAhead + 1) == '+' || regex.charAt(lookAhead + 1) == '?')){
            lookAhead++;
        }
        if (group){
            return lookAhead;
        }



        return lookAhead + 1;


    }

    private int lookAheadGroup(String regex, int pos) {

        String subString = regex.substring(pos);
        Stack<Character> squareBrackets = new Stack<>();
        int i = 0;
        do {

            if (regex.charAt(pos + i) == '[') {
                squareBrackets.push('[');
            }
            if (regex.charAt(pos + i) == ']') {
                squareBrackets.pop();
            }
            i++;
        } while (!squareBrackets.isEmpty());
        return i;

    }

    private GroupParseResult parseGroup(String regex) {
        int pos = 0;
        List<IMatcher> myMatchers = new ArrayList<>();

        int length = lookAhead(regex, pos);
        String subString = regex.substring(pos + 1, pos + length -1 );
        Quantifier quantifier = getQuantifier(subString);
        if (subString.startsWith("^")){
            subString = subString.substring(1);
            return new GroupParseResult(length, new NegativeGroupMatcher(parseMatcher(subString), quantifier));


        }


        GroupMatcher matcher = new GroupMatcher(parseMatcher(subString), quantifier
        );

        return new GroupParseResult(length, matcher);



    }

    record GroupParseResult(int size, IMatcher matcher) {
    }


}
