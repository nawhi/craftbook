package craftbook;

public class WallCommand implements Command {

	private User targetUser;
	
	public WallCommand(User u) {
		targetUser = u;
	}

	@Override
	public void execute() {
		WallView view = new WallView(targetUser, System.out);
		view.show();
	}

}
