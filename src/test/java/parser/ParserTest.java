package parser;

import matcher.AlphaNumericMatcher;
import matcher.DigitMatcher;
import matcher.GroupMatcher;
import matcher.LiteralMatcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {


    @Test
    public void testSimpleLiteral(){
        Parser parser = new Parser();
        var res = parser.parse("a").getMatchers();
        assertEquals(1, res.size());
        assertInstanceOf(LiteralMatcher.class, res.getFirst());

    }


    @Test
    public void testSimpleDigit(){

        Parser parser = new Parser();
        var res = parser.parse("\\d").getMatchers();

        assertEquals(1, res.size());
        assertInstanceOf(DigitMatcher.class, res.getFirst());

    }

    @Test
    public void testSimpleAlphaNumeric(){

        Parser parser = new Parser();
        var res = parser.parse("\\w").getMatchers();

        assertEquals(1, res.size());
        assertInstanceOf(AlphaNumericMatcher.class, res.getFirst());

    }

    @Test
    public void testThreeParsers(){

        Parser parser = new Parser();
        var res = parser.parse("\\wa\\d").getMatchers();

        assertEquals(3, res.size());
        assertInstanceOf(AlphaNumericMatcher.class, res.getFirst());
        assertInstanceOf(LiteralMatcher.class, res.get(1));
        assertInstanceOf(DigitMatcher.class, res.get(2));

    }

    @Test
    public void testGroup(){

        Parser parser = new Parser();
        var res = parser.parse("[a]").getMatchers();

        assertEquals(1, res.size());
        assertInstanceOf(GroupMatcher.class, res.getFirst());
        GroupMatcher groupMatcher = (GroupMatcher) res.getFirst();
        assertEquals(1, groupMatcher.getSubMatchers().size());
        assertInstanceOf(LiteralMatcher.class, groupMatcher.getSubMatchers().getFirst());



    }

    @Test
    public void testNestedGroup(){

        Parser parser = new Parser();
        var res = parser.parse("[[a]]").getMatchers();

        assertEquals(1, res.size());
        assertInstanceOf(GroupMatcher.class, res.getFirst());
        GroupMatcher groupMatcher = (GroupMatcher) res.getFirst();
        assertEquals(1, groupMatcher.getSubMatchers().size());
        assertInstanceOf(GroupMatcher.class, groupMatcher.getSubMatchers().getFirst());
        GroupMatcher subGroupMatcher = (GroupMatcher) groupMatcher.getSubMatchers().getFirst();
        assertEquals(1, subGroupMatcher.getSubMatchers().size());
        assertInstanceOf(LiteralMatcher.class, subGroupMatcher.getSubMatchers().getFirst());

    }

    @Test
    public void testNestedGroupWithMoreStuff(){

        Parser parser = new Parser();
        var res = parser.parse("a[[a]]\\d").getMatchers();

        assertEquals(3, res.size());
        assertInstanceOf(LiteralMatcher.class, res.getFirst());
        assertInstanceOf(GroupMatcher.class, res.get(1));
        GroupMatcher subGroupMatcher = (GroupMatcher) res.get(1);
        assertEquals(1, subGroupMatcher.getSubMatchers().size());
        assertInstanceOf(GroupMatcher.class, subGroupMatcher.getSubMatchers().getFirst());

        assertInstanceOf(DigitMatcher.class, res.get(2));
    }





}