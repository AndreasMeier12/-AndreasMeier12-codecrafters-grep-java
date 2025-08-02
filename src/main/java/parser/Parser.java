package parser;

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
        while (pos <  regex.length()) {
            int lookAhead = lookAhead(pos);
            if (regex.substring(pos, pos + lookAhead).equals("\\d")){
                res.add(new DigitMatcher());
            } else {
                res.add(new LiteralMatcher(regex.charAt(pos))) ;
            }
            pos = pos + lookAhead;
        }
        return res;
    }

    private int lookAhead(int pos) {
        int lookAhead = 0;

        while (regex.charAt(pos + lookAhead) == '\\'){
            lookAhead++;
        }


        return lookAhead + 1;


    }

}
