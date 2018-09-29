package craftbook;

public class PostCommand extends Command {
	
	private String message;
	
	public PostCommand(User u, String msg) {
		super(u);
		message = msg;
	}

	@Override
	public void execute() {
		 targetUser.post(message);
	}

}
