package tester;

import matcher.AlphaNumericMatcher;
import matcher.IMatcher;
import matcher.LiteralMatcher;
import matcher.MatcherExpression;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {


    @Test
    public void testSimpleLiterals(){
        assertTrue(Tester.test(new MatcherExpression(List.of(new LiteralMatcher('a')), false, false), "a"));
        assertFalse(Tester.test(new MatcherExpression(List.of(new LiteralMatcher('a')), false, false), "b"));

    }

    @Test
    public void testEmpty(){
        assertTrue(Tester.test(new MatcherExpression(List.of(), false, false), "a"));
        assertFalse(Tester.test(new MatcherExpression(List.of(new LiteralMatcher('a')), false, false), ""));
    }

    @Test
    public void testMoreLiteralsThanEntriesInString(){
        assertTrue(Tester.test(new MatcherExpression(List.of(new LiteralMatcher('a')), false, false), "a"));
        assertFalse(Tester.test(new MatcherExpression(List.of(new LiteralMatcher('a')), false, false), "b"));

    }

    @Test
    public void testAlphaNumeric(){
        List<IMatcher> alphaNumericMatchers = List.of(new AlphaNumericMatcher());
        assertTrue(Tester.test(new MatcherExpression(alphaNumericMatchers, false, false), "a"));
        assertFalse(Tester.test(new MatcherExpression(alphaNumericMatchers, false, false), ""));

    }

    @Test
    public void testLongerString(){
        String a = "aaaaaaaaaa";
        List<IMatcher> matchers = new ArrayList<>();
        for (int i = 0; i < a.length(); i++){
            if (i % 2 == 0){
                matchers.add(new LiteralMatcher('a'));
            } else {
                matchers.add(new AlphaNumericMatcher());
            }
        }

        assertTrue(Tester.test(new MatcherExpression(matchers, false, false), a));

    }




}