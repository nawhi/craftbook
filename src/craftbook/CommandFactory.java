package craftbook;

import java.util.NoSuchElementException;

public class CommandFactory {
	private class Commands {
		private static final String POST = "->";
		private static final String PROFILE = "";
		private static final String FOLLOW = "follows";
		private static final String WALL = "wall";
		private static final String HELP = "help";
	}
	
	private Model model;
	
	public CommandFactory(Model m) {
		model = m;
	}
	
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
		case Commands.HELP:
			return new HelpCommand();
		default:
			throw new IllegalArgumentException("Unrecognised command: " + commandText);
		}
	}
	
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
