package parser;

import matcher.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum SpecialCharacter {
    OPEN_BRACKET("\\[", x -> new LiteralMatcher('[', x)),
    CLOSE_BRACKET("\\]", x -> new LiteralMatcher('[', x)),
    ALPHA_NUMERIC("\\w", x -> new AlphaNumericMatcher(x)),
    DIGIT("\\d", x -> new DigitMatcher(x)),
    PLUS("\\+", x -> new LiteralMatcher('[', x)),
    MINUS("\\-", x -> new LiteralMatcher('[', x)),
    OPEN_PARENTHESIS("\\(", x -> new LiteralMatcher('[', x)),
    CLOSE_PARENTHESIS("\\)", x -> new LiteralMatcher('[', x)),
    SLASH("\\\\", x -> new LiteralMatcher('\\', x));


    private final String identifier;
    private final Function<Quantifier, IMatcher> matcherFunction;


    SpecialCharacter(String identifier, Function<Quantifier, IMatcher> matcherFunction) {
        this.identifier = identifier;
        this.matcherFunction = matcherFunction;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Function<Quantifier, IMatcher> getMatcherFunction() {
        return matcherFunction;
    }

    public static Optional<IMatcher> tryMatch(String a, Quantifier quantifier) {
        return Arrays.stream(SpecialCharacter.values()).filter(x -> x.identifier.equals(a))
                .findFirst().map(x -> x.getMatcherFunction().apply(quantifier));
    }
}
