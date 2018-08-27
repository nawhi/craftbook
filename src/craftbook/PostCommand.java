package craftbook;

public class PostCommand extends Command {
	
	private String message;
	
	/**
	 * @param u the User for whom to create a new post
	 * @param msg the text content of the post
	 */
	public PostCommand(User u, String msg) {
		super(u);
		message = msg;
	}

	@Override
	public void execute() {
		 targetUser.post(message);
	}

}
