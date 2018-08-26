package craftbook;

public class WallCommand extends Command {

	public WallCommand(User u) {
		super(u);
	}

	@Override
	public void execute() {
		WallView view = new WallView(targetUser, System.out);
		view.show();
	}

}
