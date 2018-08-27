package craftbook;

/**
 * Class which creates and returns the
 * right Command for the given user input tokens.
 * @author nick
 *
 */
public class CommandFactory {
	private class Commands {
		private static final String POST = "->";
		private static final String PROFILE = "";
		private static final String FOLLOW = "follows";
		private static final String WALL = "wall";
	}
	
	private Model model;
	
	/**
	 * @param m the Model singleton for this instance of the application
	 */
	public CommandFactory(Model m) {
		model = m;
	}
	
	/**
	 * Create a new Command formed from the specified token list.
	 * @param tokens the command tokens
	 * @return Command instance representing the specified parameters
	 */
	public Command makeCommand(TokenList tokens) {
		String userHandle = tokens.getUsername();
		String arg = tokens.getParameter();
		String commandText = tokens.getCommand();

		switch(commandText)
		{
		case Commands.POST:
			User user = model.hasUser(userHandle) 
						? model.getUser(userHandle) 
						: model.createUser(userHandle);
			return new PostCommand(user, arg);
		case Commands.PROFILE:
			return new ProfileCommand(model.getUser(userHandle));
		case Commands.FOLLOW:
			return new FollowCommand(model.getUser(userHandle), model.getUser(arg));
		case Commands.WALL:
			return new WallCommand(model.getUser(userHandle));
		default:
			throw new IllegalArgumentException("Unrecognised command: " + commandText);
		}
	}
}
