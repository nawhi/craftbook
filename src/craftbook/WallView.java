package craftbook;

import java.io.PrintStream;

/**
 * Class which produces a text-based view of an
 * aggregated list of a user's profile, and all
 * the profiles of other users followed by it.
 * 
 *  Example:
 * 
 *  Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
 *  Bob - Good game though. (1 minute ago)
 *  Bob - Damn! We lost! (2 minutes ago)
 *  Alice - I love the weather today (5 minutes ago)
 * 
 * @author nick
 *
 */
public class WallView extends View {

	public WallView(User user) {
		super(user);
	}
	
	public WallView(User user, PrintStream ostream) {
		super(user, ostream);
	}

	@Override
	public String calculate() {
		throw new RuntimeException("TODO");
	}

}
