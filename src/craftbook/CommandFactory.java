package craftbook;

import java.util.NoSuchElementException;

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
			return new ProfileCommand(fetchUser(userHandle));
		case Commands.FOLLOW:
			return new FollowCommand(fetchUser(userHandle), fetchUser(arg));
		case Commands.WALL:
			return new WallCommand(fetchUser(userHandle));
		default:
			throw new IllegalArgumentException("Unrecognised command: " + commandText);
		}
	}
	
	/**
	 * Wrapper around Model.getUser() which throws a more user-friendly
	 * error message.
	 * @param handle the handle of the user to find
	 * @return reference to the User object with the given handle
	 */
	private User fetchUser(String handle) {
		try {
			return model.getUser(handle);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException(
				"User '" + handle + "' doesn't exist yet!\n"
				+ "Users are created when they post their first message."
			);
		}
	}
}
