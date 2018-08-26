package craftbook;

public class FollowCommand extends Command {

	private final User userToFollow;
	
	/**
	 * Create a new instance of FollowCommand.
	 * @param u the user for whom to execute the command
	 * @param t the user who is to be followed by u
	 */
	public FollowCommand(User u, User t) {
		super(u);
		userToFollow = t;
	}

	@Override
	public void execute() {
		targetUser.follow(userToFollow);
	}

}
