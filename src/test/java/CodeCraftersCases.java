import matcher.IMatcher;
import matcher.MatcherExpression;
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
        MatcherExpression matchers = new Parser().parse(pattern);
        assertEquals(1, matchers.getMatchers().size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testMr9Case2(){
        String input = "×+%-÷=";
        String pattern = "\\w";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertEquals(1, matchers.getMatchers().size());
        boolean res = Tester.test(matchers, input);
        assertFalse(res);


    }

    @Test
    public void testTl6Raspberry(){
        String input = "a";
        String pattern = "[raspberry]";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertEquals(1, matchers.getMatchers().size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }


    @Test
    public void testMr9Intro(){
        String input = "\"%+×_÷+-";
        String pattern = "\\w";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertEquals(1, matchers.getMatchers().size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testSh9Intro(){
        String input = "1 apple";
        String pattern = "\\d apple";
        MatcherExpression matchers = new Parser().parse(pattern);
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testSh9IntroCase2(){
        String inputPositive = "100 apple";
        String inputNegative = "1 apple";

        String pattern = "\\d\\d\\d apple";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertTrue(Tester.test(matchers, inputPositive));
        assertFalse(Tester.test(matchers, inputNegative));


    }

    @Test
    public void testSh9IntroCase3(){
        String inputPositive = "3 dogs";
        String inputNegative = "1 dog";

        String pattern = "\\d \\w\\w\\ws";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertTrue(Tester.test(matchers, inputPositive));
        assertFalse(Tester.test(matchers, inputNegative));


    }


    @Test
    public void testRk3Intro(){
        String inputPositive = "dog";
        String inputNegative = "cab";

        String pattern = "[^abc]";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertTrue(Tester.test(matchers, inputPositive));
        assertFalse(Tester.test(matchers, inputNegative));


    }

    @Test
    public void testRk3Case1(){
        String inputPositive = "apple";

        String pattern = "[^abc]";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertTrue(Tester.test(matchers, inputPositive));


    }

    @Test
    public void testRk3Case2(){
        String inputNegative = "banana";

        String pattern = "[^anb]";
        MatcherExpression matchers = new Parser().parse(pattern);
        assertFalse(Tester.test(matchers, inputNegative));


    }


}
