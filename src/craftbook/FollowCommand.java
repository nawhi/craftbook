package craftbook;

public class FollowCommand implements Command {

	private final User targetUser;
	private final User userToFollow;
	
	public FollowCommand(User u, User t) {
		targetUser = u;
		userToFollow = t;
	}

	@Override
	public void execute() {
		targetUser.follow(userToFollow);
	}

}
