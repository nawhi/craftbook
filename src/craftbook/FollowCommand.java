package craftbook;

public class FollowCommand extends Command {

	private final User userToFollow;
	
	public FollowCommand(User u, User t) {
		super(u);
		userToFollow = t;
	}

	@Override
	public void execute() {
		targetUser.follow(userToFollow);
	}

}
