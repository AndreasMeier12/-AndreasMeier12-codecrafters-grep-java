package parser;

import matcher.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    String regex;

    public Parser() {
            }

    public List<IMatcher> parseMatcher(String regex) {
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

        switch (subString) {
            case "\\d":
                return new GroupParseResult(2, new DigitMatcher());

            case "\\w":
                return new GroupParseResult(2, new AlphaNumericMatcher());

            default:
                if (subString.length() > 1) {
                    throw new RuntimeException("Character \"" + subString + "\" not implemented");
                }
                return new GroupParseResult(1, new LiteralMatcher(subString.charAt(0)));

        }
    }

    private int lookAhead(String regex, int pos) {
        int lookAhead = 0;

        while (regex.charAt(pos + lookAhead) == '\\') {
            lookAhead++;
        }
        while (regex.charAt(pos + lookAhead) == '[') {
            return lookAheadGroup(regex, pos);
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
        if (subString.startsWith("^")){
            subString = subString.substring(1);
            return new GroupParseResult(length, new NegativeGroupMatcher(parseMatcher(subString)));


        }


        GroupMatcher matcher = new GroupMatcher(parseMatcher(subString)
        );

        return new GroupParseResult(length, matcher);



    }

    record GroupParseResult(int size, IMatcher matcher) {
    }


}
