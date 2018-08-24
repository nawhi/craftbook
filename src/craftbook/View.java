package craftbook;

import java.io.PrintStream;
import java.time.Instant;

/**
 * Base class for views of user data.
 * @author nick
 */
public abstract class View {

	/**
	 * Create a new instance of the view, which
	 * prints its output to System.out.
	 * @param user the User whose data is to be viewed
	 */
	public View(User user) {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * Create a new instance of the view, which
	 * prints its output to the specified output stream.
	 * @param user the User whose data is to be viewed
	 * @param ostream The output stream to print the view to
	 */
	public View(User user, PrintStream ostream) {
		throw new RuntimeException("not implemented");
	}
	
	public abstract void show();
	public abstract String calculate();
	
	/**
	 * Produce a description of the difference between two times
	 * in a way appropriate for display to the user. Will display
	 * the difference in the highest-grained nonzero time unit only.
	 * @param a the first Instant to compare
	 * @param b the second Instant to compare 
	 * @return string describing the time interval between 
	 * 		   start and end. For example: "moments", "3 seconds",
	 * 		   "5 minutes", "7 days"
	 */
	public static String calcTimespan(Instant a, Instant b) {
		throw new RuntimeException("not implemented");
	}
}
