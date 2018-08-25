package craftbook;

/**
 * Class representing a command made by the user in the 
 * console.
 * @author nick
 *
 */
public abstract class Command {

	/**
	 * Virtual constructor with common initialisation code
	 * only.
	 * @param m the Model singleton for this instance of 
	 *        the application
	 * @param u the User to execute this command on
	 */
	public Command(Model m, User u) {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * Carry out the command.
	 */
	public abstract void execute();
	

}
