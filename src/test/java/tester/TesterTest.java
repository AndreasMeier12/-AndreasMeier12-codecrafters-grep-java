package tester;

import matcher.LiteralMatcher;
import org.junit.jupiter.api.Test;

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




}