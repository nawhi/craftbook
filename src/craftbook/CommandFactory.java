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
	 * Create a new CommandFactory.
	 * @param m the Model singleton for this instance of the application
	 */
	public CommandFactory(Model m) {
		model = m;
	}
	
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
		User user = ensureUser(userHandle);
		
		if (commandText.isEmpty())
			return new ProfileCommand(user);
		
		switch(commandText)
		{
		case "->":
			return new PostCommand(user, arg);
		case "wall":
			return new WallCommand(user);
		case "follows":
			return new FollowCommand(user, ensureUser(arg));
		default:
			throw new IllegalArgumentException("Unrecognised command: " + commandText);
		}
	}
	
	public Command makeCommand(TokenList tokens) {
		return makeCommand(tokens.getUsername(), tokens.getCommand(), tokens.getParameter());
	}
	
	/**
	 * Fetch the user with the specified handle, or create
	 * them if they do not exist yet.
	 * @param handle the handle of the user to find or create
	 * @return reference to User with the given handle 
	 */
	private User ensureUser(String handle) {
		if (model.hasUser(handle))
			return model.getUser(handle);
		return model.createUser(handle);
	}
}
