package craftbook;

import java.text.ParseException;

class TokenList {
	public String username;
	public String command;
	public String parameter;
	
	public TokenList(String u, String c, String p) {
		username = u;
		command = c;
		parameter = p;
	}
	
	@Override public boolean equals(Object other) {
		if (other instanceof TokenList) {
			return username == ((TokenList)other).username
				&& command == ((TokenList)other).command
				&& parameter == ((TokenList)other).parameter;
		}
		return false;
	}
}

/**
 * Static class responsible for parsing user input
 * on the console.
 * @author nick
 *
 */
public final class CommandParser {

	/**
	 * Parse a line of user input into three tokens that can be used
	 * to generate a Command object for the command.
	 * @param line the raw line, containing a user-inputted command, to read
	 * @return an array of three Strings representing the user, command,
	 *         and parameter tokens, where all three are non-null, user
	 *         is nonempty and command and parameter may be empty.
	 *         Whitespace is ignored in the first two tokens but included
	 *         in the third (after the first space).
	 * @throws ParseException if the input cannot be tokenised
	 */
	public static TokenList parse(String line) throws ParseException {
		throw new RuntimeException("TODO");
	}
}
