package craftbook;

/**
 * Class whose makeCommand() method creates and returns the
 * right Command for the given action.
 * @author nick
 *
 */
public class CommandFactory {
	private Model model;
	
	/**
	 * Create a new Command formed from the specified parameters.
	 * @param user The nonempty handle of the user for which to execute 
	 *        the command.  If this user does not yet exist in the model,
	 *        it will e 
	 * @param commandType The text of the command itself, which may be
	 *        the empty string.
	 * @param arg The argument supplied with the command, which may be
	 *        the empty string.
	 * @return
	 */
	public Command makeCommand(String userHandle, String commandText, String arg) {
		User user;
		if (!model.hasUser(userHandle))
			user = model.createUser(userHandle);
		else
			user = model.getUser(userHandle);
		
		if (commandText.isEmpty())
			return new ProfileCommand(user);
		
		if (commandText.equals("->"))
			return new PostCommand(user, arg);
		
		throw new IllegalArgumentException("Could not understand parameters");
	}
	
	public Command makeCommand(TokenList tokens) {
		return makeCommand(tokens.username, tokens.command, tokens.parameter);
	}
	
	/**
	 * Create a new CommandFactory.
	 * @param m the Model singleton for this instance of the application
	 */
	public CommandFactory(Model m) {
		model = m;
	}
}
