package parser;

import matcher.DigitMatcher;
import matcher.LiteralMatcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {


    @Test
    public void testSimpleLiteral(){
        Parser parser = new Parser("a");
        var res = parser.parse();
        assertEquals(1, res.size());
        assertInstanceOf(LiteralMatcher.class, res.getFirst());

    }


    @Test
    public void testSimpleDigit(){

        Parser parser = new Parser("\\d");
        var res = parser.parse();

        assertEquals(1, res.size());
        assertInstanceOf(DigitMatcher.class, res.getFirst());

    }




}