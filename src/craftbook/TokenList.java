package craftbook;

/**
 * Class representing a user command which has
 * been parsed into tokens.
 * 
 * @author nick
 *
 */
public class TokenList {
	private final String username;
	private final String command;
	private final String parameter;
	
	/**
	 * @param u The handle of the user for which to execute 
	 *        the command, which be nonempty.
	 * @param c The text of the command itself, which may be
	 *        the empty string.
	 * @param p The argument supplied with the command, which may be
	 *        the empty string.
	 */
	public TokenList(String u, String c, String p) {
		username = u;
		command = c;
		parameter = p;
	}

	public String getUsername() {
		return username;
	}

	public String getCommand() {
		return command;
	}

	public String getParameter() {
		return parameter;
	}
	
	@Override 
	public boolean equals(Object other) {
		if (other instanceof TokenList) {
			return username.equals(((TokenList)other).username)
				&& command.equals(((TokenList)other).command)
				&& parameter.equals(((TokenList)other).parameter);
		}
		return super.equals(other);
	}
}
