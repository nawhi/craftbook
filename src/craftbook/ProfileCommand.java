package craftbook;

/**
 * Command which, when executed, shows a view of a 
 * particular user's past posts (via ProfileView).
 * @author nick
 *
 */
public class ProfileCommand extends Command {
	
	/**
	 * Create a new ProfileCommand.
	 * @param m the Model singleton for this instance 
	 *        of the application
	 * @param u the User on which to execute this command
	 */
	protected ProfileCommand(User u) {
		super(u);
	}

	@Override
	public void execute() {
		 ProfileView pview = new ProfileView(targetUser, System.out);
		 pview.show();
	}

}
