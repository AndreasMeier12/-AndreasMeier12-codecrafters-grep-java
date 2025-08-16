package matcher;

import java.util.List;

public class NegativeGroupMatcher implements IMatcher {

    List<IMatcher> subMatchers;


    public NegativeGroupMatcher(List<IMatcher> subMatchers) {
        this.subMatchers = subMatchers;
    }

    @Override
    public String getType() {
        return "NegativeGroup";
    }

    @Override
    public MatcherResponse match(String a) {
        int pos = 0;
        for (IMatcher matcher : subMatchers){
            String subString = a.substring(pos);
            MatcherResponse match = matcher.match(subString);
            if(match.isMatches()){
                return new MatcherResponse(false, 0, false);
            }
            pos = pos + match.getCharsToConsume();
        }

        return new MatcherResponse(true, 1, true);

    }
}
