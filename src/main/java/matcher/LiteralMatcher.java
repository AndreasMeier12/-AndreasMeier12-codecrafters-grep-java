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

        int i = 0;
            if (a.charAt(i) == val){
                return new MatcherResponse(true, i + 1, true);
            }

        return new MatcherResponse(false, 0, false);

    }


}
