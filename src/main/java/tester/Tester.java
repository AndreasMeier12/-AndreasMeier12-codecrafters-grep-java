package tester;

import matcher.IMatcher;

import java.util.List;

public class Tester {
    public static boolean test(List<IMatcher> matchers, String a) {
        int matcherPos = 0;
        int stringPos = 0;

        while (stringPos < a.length() && matcherPos < matchers.size()) {
            IMatcher matcher = matchers.get(matcherPos);

            IMatcher.MatcherResponse match = matcher.match(a.substring(stringPos));
            if (!match.isMatches()) {
                return false;

            }
            stringPos = stringPos + match.getCharsToConsume();

            if (match.isConsumeMatcher()) {
                matcherPos++;
            }


        }
        if (matcherPos == matchers.size()) {
            return true;
        }

        return false;


    }

}
