package matcher;

public interface IMatcher {

    String getType();


    MatcherResponse match(String a);

    public static class MatcherResponse{
        boolean matches;
        int charsToConsume;
        boolean consumeMatcher;

        public MatcherResponse(boolean matches, int charsToConsume, boolean consumeMatcher) {
            this.matches = matches;
            this.charsToConsume = charsToConsume;
            this.consumeMatcher = consumeMatcher;
        }

        public boolean isMatches() {
            return matches;
        }

        public int getCharsToConsume() {
            return charsToConsume;
        }

        public boolean isConsumeMatcher() {
            return consumeMatcher;
        }
    }

}
