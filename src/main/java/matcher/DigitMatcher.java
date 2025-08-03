package matcher;

public class DigitMatcher implements IMatcher {
    @Override
    public String getType() {
        return "Digit";
    }

    @Override
    public MatcherResponse match(String a) {
        if (Character.isDigit(a.charAt(0))) {
            return new MatcherResponse(true, 1, true);
        } else
            return new MatcherResponse(false, 0, false);


    }


}
