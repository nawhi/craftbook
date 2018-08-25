package craftbook;

/**
 * Class representing a command made by the user in the 
 * console.
 * @author nick
 *
 */
public abstract class Command {

	protected User targetUser;
	
	/**
	 * Virtual constructor with common initialisation code
	 * only.
	 * @param u the User to execute this command on
	 */
	public Command(User u) {
		targetUser = u;
	}
	
	/**
	 * Carry out the command.
	 */
	public abstract void execute();
	

}
