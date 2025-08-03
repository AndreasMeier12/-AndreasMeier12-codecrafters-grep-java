package parser;

import matcher.AlphaNumericMatcher;
import matcher.DigitMatcher;
import matcher.IMatcher;
import matcher.LiteralMatcher;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    String regex;

    public Parser(String regex) {
        this.regex = regex;
    }

    public List<IMatcher> parse() {
        List<IMatcher> res = new ArrayList<>();

        int pos = 0;
        while (pos < regex.length()) {
            int lookAhead = lookAhead(pos);
            String subString = regex.substring(pos, pos + lookAhead);

            switch (subString) {
                case "\\d":
                    res.add(new DigitMatcher());

                    break;
                case "\\w":
                    res.add(new AlphaNumericMatcher());


                    break;

                default:
                    if (subString.length() > 1) {
                        throw new RuntimeException("Character \"" + subString + "\" not implemented");
                    }
                    res.add(new LiteralMatcher(subString.charAt(0)));

                    break;
            }
            pos = pos + lookAhead;
        }
        return res;
    }

    private int lookAhead(int pos) {
        int lookAhead = 0;

        while (regex.charAt(pos + lookAhead) == '\\') {
            lookAhead++;
        }


        return lookAhead + 1;


    }

}
