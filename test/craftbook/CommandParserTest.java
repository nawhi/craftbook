package craftbook;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class CommandParserTest {
	
	@Test
	public void getTokensProducesEmptyStringCorrectly() throws Exception {
		assertEquals(new TokenList("foo", "", ""), CommandParser.parse("foo"));
		assertEquals(new TokenList("foo", "bar", ""), CommandParser.parse("foo bar"));
	}
	
	@Test(expected=ParseException.class)
	public void parseFailsForEmptyString() throws Exception {
		CommandParser.parse("");
	}
	
	@Test
	public void whitespaceIsIgnoredInFirstTwoTokens() throws Exception {
		assertEquals(
			new TokenList("dave", "->", "Hello"),
			CommandParser.parse("   dave    -> Hello")
		);
	}
	
	@Test
	public void whitespaceIsIncludedInFinalToken() throws Exception {
		assertEquals(
			new TokenList("dave", "->", "    hello world    "), 
			CommandParser.parse("dave ->     hello world    ")
		);
	}
}
