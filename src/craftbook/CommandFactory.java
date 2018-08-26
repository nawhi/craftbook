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
	 * @param m the Model singleton for this instance of the application
	 */
	public CommandFactory(Model m) {
		model = m;
	}
	
	/**
	 * Create a new Command formed from the specified parameters.
	 * @param user The nonempty handle of the user for which to execute 
	 *        the command.  If this user does not exist yet and the command
	 *        is Post, the user will be created, otherwise an exception
	 *        will be thrown.
	 * @param commandType The text of the command itself, which may be
	 *        the empty string.
	 * @param arg The argument supplied with the command, which may be
	 *        the empty string.
	 * @return Command instance representing the specified parameters
	 * @throws IllegalArgumentException if there was an issue with the parameters
	 *         that meant a command could not be created
	 */
	public Command makeCommand(String userHandle, String commandText, String arg) {
		switch(commandText)
		{
		case "->":
			return new PostCommand(ensureUser(userHandle), arg);
		case "":
			return new ProfileCommand(model.getUser(userHandle));
		case "follows":
			return new FollowCommand(model.getUser(userHandle), model.getUser(arg));
		case "wall":
			return new WallCommand(model.getUser(userHandle));
		default:
			throw new IllegalArgumentException("Unrecognised command: " + commandText);
		}
	}
	
	/**
	 * Create a new Command formed from the specified token list.
	 * @param tokens the command tokens
	 * @return Command instance representing the specified parameters
	 * @throws IllegalArgumentException if there was an issue with the parameters
	 *         that meant a command could not be created
	 */
	public Command makeCommand(TokenList tokens) {
		return makeCommand(tokens.getUsername(), tokens.getCommand(), tokens.getParameter());
	}
	
	/**
	 * Fetch the user with the specified handle, or create
	 * one if not found.
	 * @param handle the handle of the user to find or create
	 * @return reference to User with the given handle 
	 */
	private User ensureUser(String handle) {
		if (model.hasUser(handle))
			return model.getUser(handle);
		return model.createUser(handle);
	}
}
