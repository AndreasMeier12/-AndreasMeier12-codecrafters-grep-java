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


}
