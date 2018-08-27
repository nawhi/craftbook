package craftbook;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

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
		List<String> tokens = Arrays.asList(line.trim().split("\\s+"));
		
		if (tokens.size() == 0)
			throw new ParseException("No tokens found", 0);
		
		if (tokens.get(0).isEmpty())
			throw new ParseException("No username found", 0);
		
		String username = tokens.get(0);
		
		String command;
		if (tokens.size() > 1)
			command = tokens.get(1);
		else
			command = "";
		
		String parameter;
		if (tokens.size() > 2)
			parameter = String.join(" ", tokens.subList(2, tokens.size()));
		else
			parameter = "";
		
		return new TokenList(username, command, parameter);
	}
}
