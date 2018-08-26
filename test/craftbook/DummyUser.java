package craftbook;

import java.time.Instant;

/**
 * Minimal User mock which allows control over the
 * timestamp of posts.
 * Tests must be careful not to supply timestamps that
 * are not in chronological order to sequential posts, 
 * as this would break the postcondition of getPosts().
 * 
 * @author nick
 *
 */
public class DummyUser extends User {
	
	public DummyUser(String handle) {
		super(handle);
	}
	
	/**
	 * Add a new post with timestamp control.
	 * NB this is an overload not override, 
	 * void post(String) is still available!
	 * @param message the text content of the post 
	 * @param timestamp time the post was created
	 */
	public void post(String message, Instant timestamp) {
		posts.add(new Post(message, timestamp, this));
	}
}
