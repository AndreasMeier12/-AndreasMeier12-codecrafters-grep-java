package matcher;

import java.util.ArrayList;
import java.util.List;

public class GroupMatcher implements IMatcher {

    List<IMatcher> subMatchers;
    Quantifier quantifier;

    public GroupMatcher(List<IMatcher> subMatchers, Quantifier quantifier) {
        this.subMatchers = subMatchers;
        this.quantifier = quantifier;
    }

    @Override
    public String getType() {
        return "Group";
    }

    @Override
    public Quantifier getQuantifier() {
        return quantifier;
    }

    @Override
    public MatcherResponse match(String a) {

        int pos = 0;
        for (IMatcher matcher : subMatchers){
            String subString = a.substring(pos);
            MatcherResponse match = matcher.match(subString);
            if(match.isMatches()){
                return match;
            }
        }

        return new MatcherResponse(false, 0, false);
    }

    public List<IMatcher> getSubMatchers() {
        return subMatchers;
    }
}
