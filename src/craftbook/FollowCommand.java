package craftbook;

public class FollowCommand extends Command {

	private final User userToFollow;
	
	/**
	 * @param u the user on whom to execute the command
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
