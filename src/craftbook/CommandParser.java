package craftbook;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public final class CommandParser {

	private static final String SPECIAL_COMMAND_PREFIX = "!";
	
	public static TokenList parse(String line) throws ParseException {
		List<String> tokens = Arrays.asList(line.trim().split("\\s+"));
		
		if (tokens.size() == 0)
			throw new ParseException("No tokens found", 0);
		
		if (tokens.get(0).startsWith(SPECIAL_COMMAND_PREFIX))
			return parseSpecial(tokens);
		return parseRegular(tokens);
	}
	
	private static TokenList parseSpecial(List<String> tokens) {
		return new TokenList("", tokens.get(0).substring(1), "");
	}
	
	private static TokenList parseRegular(List<String> tokens) throws ParseException {
		if (tokens.get(0).isEmpty())
			throw new ParseException("No username found", 0);
		
		String username = tokens.get(0);
		String command = tokens.size() > 1 ? tokens.get(1) : "";
		
		String parameter;
		if (tokens.size() > 2)
			parameter = String.join(" ", tokens.subList(2, tokens.size()));
		else
			parameter = "";
		
		return new TokenList(username, command, parameter);
	}
}
