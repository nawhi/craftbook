package craftbook;

import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;

/**
 * Base class for text-based views of user data, which
 * are printed to System.out, or the custom PrintStream
 * provided in the constructor, when show() is called.
 * @author nick
 */
public abstract class View {

	protected User targetUser;
	protected PrintStream outStream;
	
	/**
	 * Create a new instance of the view, which
	 * prints its output to System.out.
	 * @param user the User whose data is to be viewed
	 */
	public View(User user) {
		targetUser = user;
	}
	
	/**
	 * Create a new instance of the view, which
	 * prints its output to the specified output stream.
	 * @param user the User whose data is to be viewed
	 * @param ostream The output stream to print the view to
	 */
	public View(User user, PrintStream ostream) {
		targetUser = user;
		outStream = ostream;
	}
	
	/**
	 * Print the content of the view to the view's output stream.
	 */
	public void show() {
		outStream.println(calculate());
	}
	
	/**
	 * Calculate the content of the view.
	 * @return string containing the full content of the view 
	 */
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
		Duration diff = Duration.between(a,b).abs();
		
		long secs = diff.getSeconds();
		if (secs < 1)
			return "moments";
		if (secs < 60)
			return String.format("%d second%s", secs, (secs==1 ? "" : "s"));
		
		long mins = diff.toMinutes();
		if (mins < 60)
			return String.format("%d minute%s", mins, (mins==1 ? "" : "s"));
		
		long hrs = diff.toHours();
		if (hrs < 24)
			return String.format("%d hour%s", hrs, (hrs==1 ? "" : "s"));
			
		long days = diff.toDays();
		if (days < 28)
			return String.format("%d day%s", days, (days==1 ? "" : "s"));
		
		return "over a month";
	}
	
	protected Instant getCurrentTime() {
		return Instant.now();
	}
}
