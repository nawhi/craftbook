package craftbook;

public class TokenList {
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
			return username.equals(((TokenList)other).username)
				&& command.equals(((TokenList)other).command)
				&& parameter.equals(((TokenList)other).parameter);
		}
		return false;
	}
}
