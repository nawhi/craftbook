package craftbook;

public class ProfileCommand extends Command {
	
	protected ProfileCommand(User u) {
		super(u);
	}

	@Override
	public void execute() {
		 ProfileView pview = new ProfileView(targetUser, System.out);
		 pview.show();
	}

}
