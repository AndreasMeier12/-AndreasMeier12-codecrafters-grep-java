import matcher.IMatcher;
import org.junit.jupiter.api.Test;
import parser.Parser;
import tester.Tester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CodeCraftersCases {

    @Test
    public void testMr9(){
        String input = "\"%+×_÷+-";
        String pattern = "\\w";
        List<IMatcher> matchers = new Parser().parseMatcher(pattern);
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testMr9Case2(){
        String input = "×+%-÷=";
        String pattern = "\\w";
        List<IMatcher> matchers = new Parser().parseMatcher(pattern);
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertFalse(res);


    }

    @Test
    public void testTl6Raspberry(){
        String input = "a";
        String pattern = "[raspberry]";
        List<IMatcher> matchers = new Parser().parseMatcher(pattern);
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }


    @Test
    public void testMr9Intro(){
        String input = "\"%+×_÷+-";
        String pattern = "\\w";
        List<IMatcher> matchers = new Parser().parseMatcher(pattern);
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testSh9Intro(){
        String input = "1 apple";
        String pattern = "\\d apple";
        List<IMatcher> matchers = new Parser().parseMatcher(pattern);
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testSh9IntroCase2(){
        String inputPositive = "100 apple";
        String inputNegative = "1 apple";

        String pattern = "\\d\\d\\d apple";
        List<IMatcher> matchers = new Parser().parseMatcher(pattern);
        assertTrue(Tester.test(matchers, inputPositive));
        assertTrue(Tester.test(matchers, inputNegative));


    }

}
