package matcher;

import java.util.List;

public class NegativeGroupMatcher implements IMatcher {

    List<IMatcher> subMatchers;
    Quantifier quantifier;

    public NegativeGroupMatcher(List<IMatcher> subMatchers, Quantifier quantifier) {
        this.subMatchers = subMatchers;
        this.quantifier = quantifier;
    }

    @Override
    public String getType() {
        return "NegativeGroup";
    }

    @Override
    public Quantifier getQuantifier() {
        return quantifier;
    }

    @Override
    public MatcherResponse match(String a) {
        int pos = 0;
        int i = 0;

            String subString = a.substring(pos);
            if(subMatchers.stream().anyMatch(x -> x.match(subString).matches)){
                pos = pos +1;
            } else {
                return new MatcherResponse(true, pos + 1, true);
            }

        return new MatcherResponse(false, 0, true);

    }
}
