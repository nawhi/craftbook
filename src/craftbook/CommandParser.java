package craftbook;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public final class CommandParser {

	public static TokenList parse(String line) throws ParseException {
		List<String> tokens = Arrays.asList(line.trim().split("\\s+"));
		
		if (tokens.size() == 0)
			throw new ParseException("No tokens found", 0);
		
		String firstToken = tokens.get(0);
		
		if (firstToken.isEmpty())
			throw new ParseException("No username found", 0);
		
		String username, command;
		if (firstToken.startsWith("!")) {
			username = "";
			command = firstToken.substring(1, firstToken.length());
		} else {
			username = firstToken;
			command = tokens.size() > 1 ? tokens.get(1) : "";
		}
		
		String parameter;
		if (tokens.size() > 2)
			parameter = String.join(" ", tokens.subList(2, tokens.size()));
		else
			parameter = "";
		
		return new TokenList(username, command, parameter);
	}
}
