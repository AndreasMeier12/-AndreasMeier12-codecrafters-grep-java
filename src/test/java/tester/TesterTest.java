package tester;

import matcher.AlphaNumericMatcher;
import matcher.IMatcher;
import matcher.LiteralMatcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {


    @Test
    public void testSimpleLiterals(){
        assertTrue(Tester.test(List.of(new LiteralMatcher('a')), "a"));
        assertFalse(Tester.test(List.of(new LiteralMatcher('a')), "b"));

    }

    @Test
    public void testEmpty(){
        assertTrue(Tester.test(List.of(), "a"));
        assertFalse(Tester.test(List.of(new LiteralMatcher('a')), ""));
    }

    @Test
    public void testMoreLiteralsThanEntriesInString(){
        assertTrue(Tester.test(List.of(new LiteralMatcher('a')), "a"));
        assertFalse(Tester.test(List.of(new LiteralMatcher('a')), "b"));

    }

    @Test
    public void testAlphaNumeric(){
        assertTrue(Tester.test(List.of(new AlphaNumericMatcher()), "a"));
        assertFalse(Tester.test(List.of(new AlphaNumericMatcher()), ""));

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

        assertTrue(Tester.test(matchers, a));

    }




}