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
        while (pos < a.length()){

            String subString = a.substring(pos);
            if(subMatchers.stream().anyMatch(x -> x.match(subString).matches)){
                pos = pos +1;
            } else {
                return new MatcherResponse(true, pos + 1, true);
            }
        }

        return new MatcherResponse(false, 0, true);

    }
}
