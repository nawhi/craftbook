package craftbook;

public class PostCommand implements Command {
	
	private User targetUser;
	private String message;
	
	public PostCommand(User u, String msg) {
		targetUser = u;
		message = msg;
	}

	@Override
	public void execute() {
		 targetUser.post(message);
	}

}
