package craftbook;

public class CommandFactory {
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
	public static Command makeCommand(String userHandle, String commandText, String arg) {
		throw new RuntimeException("not implemented");
	}
}
