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
	public void getTokensHandlesHelpCommandCorrectly() throws Exception {
		assertEquals(new TokenList("", "help", ""), CommandParser.parse("!help"));
	}
	
	@Test
	public void leadingAndTrailingWhitespaceIsStripped() throws Exception {
		assertEquals(
			new TokenList("dave", "->", "Hello world"),
			CommandParser.parse("   dave    ->    Hello world    ")
		);
	}
}
