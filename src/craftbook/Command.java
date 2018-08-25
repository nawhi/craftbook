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
	 * only. Constructors for concrete instances of Command 
	 * are only called by the static creation method from().
	 * @param m the Model singleton for this instance of 
	 *        the application
	 */
	protected Command(Model m, User u) {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * Carry out the command.
	 */
	public abstract void execute();
	
	/**
	 * Create a new Command formed from the specified parameters.
	 * @param user The nonempty handle of the user for which to execute 
	 *        the command. 
	 * @param commandType The text of the command itself, which may be
	 *        the empty string.
	 * @param arg The argument supplied with the command, which may be
	 *        the empty string.
	 * @return
	 */
	public static Command from(String userHandle, String commandText, String arg) {
		throw new RuntimeException("not implemented");
	}
}
