package craftbook;

import java.io.PrintStream;

/**
 * Class that produces a text-based view of a user's
 * profile and prints it to an output stream specified
 * in the constructor. 
 * This takes the form of a list of posts, most recent
 * first, and detail of how long ago the posts were
 * created.
 * 
 *  Example:
 *  
 *  Good game though. (1 minute ago)
 *  Damn! We lost! (2 minutes ago)
 *  
 * @author nick
 *
 */
public class ProfileView extends View {

	public ProfileView(User user) {
		super(user);
	}
	
	public ProfileView(User user, PrintStream ostream) {
		super(user, ostream);
	}
	
	/**
	 * Calculate the content of the view and print it
	 * to the output stream.
	 */
	@Override
	public void show() {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * @return string containing the full view of this user's
	 * 		   profile 
	 */
	@Override
	public String calculate() {
		throw new RuntimeException("not implemented");
	}
}
