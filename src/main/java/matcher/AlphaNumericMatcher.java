package matcher;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AlphaNumericMatcher implements IMatcher {

    private static Logger logger = Logger.getLogger(AlphaNumericMatcher.class.getName());

    Quantifier quantifier;

    public AlphaNumericMatcher(Quantifier quantifier) {

    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public Quantifier getQuantifier() {
        return null;
    }

    @Override
    public MatcherResponse match(String a) {
        char c = a.charAt(0);

        logger.log(Level.INFO, "Matching alphanumeric to value in string " + c);

        int i = 0;
            if ( matchChar(a.charAt(i)) ){
                return new MatcherResponse(true, i + 1, true);
            }


        return new MatcherResponse(false, 0, false);

    }

    private boolean matchChar(char c){
        return Character.isDigit(c) || Character.isAlphabetic(c) || c == '_';
    }


}
