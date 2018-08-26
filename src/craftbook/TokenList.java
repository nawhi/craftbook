package craftbook;

public class TokenList {
	private final String username;
	private final String command;
	private final String parameter;
	
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
