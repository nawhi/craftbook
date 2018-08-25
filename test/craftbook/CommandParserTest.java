package craftbook;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class CommandParserTest {
	
	@Test
	public void getTokensProducesEmptyStringCorrectly() throws Exception {
		assertEquals(new TokenList("foo", "", ""), CommandParser.getTokens("foo"));
		assertEquals(new TokenList("foo", "bar", ""), CommandParser.getTokens("foo bar"));
	}
	
	@Test(expected=ParseException.class)
	public void parseFailsForEmptyString() throws Exception {
		CommandParser.parse("");
	}
	
	@Test
	public void whitespaceIsIgnoredInFirstTwoTokens() throws Exception {
		assertEquals(
			new TokenList("foo", "bar", "baz"),
			CommandParser.getTokens("   foo    bar baz")
		);
	}
	
	@Test
	public void whitespaceIsIncludedInFinalToken() throws Exception {
		assertEquals(
			new TokenList("foo", "bar", "    baz    "), 
			CommandParser.getTokens("foo bar     baz    ")
		);
	}

}
