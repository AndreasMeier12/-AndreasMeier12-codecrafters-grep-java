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
        List<IMatcher> matchers = new Parser(pattern).parse();
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertTrue(res);


    }

    @Test
    public void testMr9Case2(){
        String input = "×+%-÷=";
        String pattern = "\\w";
        List<IMatcher> matchers = new Parser(pattern).parse();
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertFalse(res);


    }

    public void testMr9Intro(){
        String input = "\"%+×_÷+-";
        String pattern = "\\w";
        List<IMatcher> matchers = new Parser(pattern).parse();
        assertEquals(1, matchers.size());
        boolean res = Tester.test(matchers, input);
        assertFalse(res);


    }

}
