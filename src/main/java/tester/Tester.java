package tester;

import matcher.IMatcher;
import matcher.MatcherExpression;

import java.util.List;

public class Tester {
    public static boolean test(MatcherExpression matcherExpression, String a) {

        int maxAdvance = matcherExpression.isStartAnchored() ? 0 : a.length();
        for ( int i = 0; i <= maxAdvance; i++){
            String candidate = a.substring(i);
            if (testInner(matcherExpression, candidate)){
                return true;
            }

        }

        return false;





    }

    static boolean testInner(MatcherExpression matcherExpression, String a){
        int matcherPos = 0;
        int stringPos = 0;


        List<IMatcher> matchers = matcherExpression.getMatchers();

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
