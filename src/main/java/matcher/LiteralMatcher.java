package matcher;

public class LiteralMatcher implements IMatcher {
    private final char val;

    public LiteralMatcher(char val) {
        this.val = val;
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public MatcherResponse match(String a) {
        if (a.charAt(0) == this.val) {
            return new MatcherResponse(true, 1, true);
        } else
            return new MatcherResponse(false, 0, false);

    }


}
