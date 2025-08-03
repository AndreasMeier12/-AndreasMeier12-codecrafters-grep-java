package matcher;

public class DigitMatcher implements IMatcher {
    @Override
    public String getType() {
        return "Digit";
    }

    @Override
    public MatcherResponse match(String a) {
        for (int i = 0; i < a.length(); i++){
            if (Character.isDigit(a.charAt(i))){
                return new MatcherResponse(true, i + 1, true);
            }

        }
        return new MatcherResponse(false, 0, false);


    }


}
