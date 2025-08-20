package tester;

import matcher.IMatcher;
import matcher.MatcherExpression;

import java.util.ArrayList;
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

            QuantifiedMatchResult quantifiedMatchResult = getNumMatches(a, stringPos, matcher);

            if (!quantifiedMatchResult.isOkay()) {
                return false;

            }
            stringPos = stringPos + quantifiedMatchResult.getCharsToConsume();

            if (quantifiedMatchResult.consumeMatcher()) {
                matcherPos++;
            }


        }
        if (matcherPos == matchers.size()) {
            if (matcherExpression.isEndAnchored() && stringPos < a.length()){
                return false;
            }

            return true;
        }

        return false;


    }

    private static QuantifiedMatchResult getNumMatches(String a,int stringPos, IMatcher matcher){
        List<IMatcher.MatcherResponse> responses = new ArrayList<>();

        while (responses.size() < matcher.getQuantifier().getMaxTimes() && responses.stream().allMatch(x -> x.isMatches())){
            IMatcher.MatcherResponse match = matcher.match(a.substring(stringPos));
            if (match.isConsumeMatcher()) {
                stringPos = stringPos + match.getCharsToConsume();
            }
            responses.add(match);

        }
        return new QuantifiedMatchResult(matcher, responses);

    }


    record QuantifiedMatchResult(IMatcher matcher, List<IMatcher.MatcherResponse> responses){


        int getNumMatches(){
            return Math.toIntExact(responses.stream().filter(x -> x.isMatches()).count());
        }

        int getCharsToConsume() {




            return responses.stream().filter(x -> x.isConsumeMatcher()).map(x -> x.getCharsToConsume()).mapToInt(Integer::intValue)
                    .sum();
        }

        boolean isOkay(){
            int numMatches = getNumMatches();
            return matcher.getQuantifier().getMinTimes() <= numMatches && matcher.getQuantifier().getMaxTimes() >= numMatches;
        }

        boolean consumeMatcher(){
            return true;
        }
    }

}
