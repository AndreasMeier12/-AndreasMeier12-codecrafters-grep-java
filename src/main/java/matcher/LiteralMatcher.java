package matcher;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LiteralMatcher implements IMatcher {
    private final char val;

    private static Logger logger = Logger.getLogger(LiteralMatcher.class.getName());

    public LiteralMatcher(char val) {
        this.val = val;
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public MatcherResponse match(String a) {
        logger.log(Level.INFO, "Matching literal " + val + " to value in string " + a.charAt(0));

        if (a.charAt(0) == this.val) {
            return new MatcherResponse(true, 1, true);
        } else
            return new MatcherResponse(false, 0, false);

    }


}
