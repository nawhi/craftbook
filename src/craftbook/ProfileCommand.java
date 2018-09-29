package craftbook;

public class ProfileCommand implements Command {
	
	private User targetUser;
	
	protected ProfileCommand(User u) {
		targetUser = u;
	}

	@Override
	public void execute() {
		 ProfileView pview = new ProfileView(targetUser, System.out);
		 pview.show();
	}

}
