package craftbook;

import java.time.Instant;

/**
 * Immutable data class representing a post made by 
 * a user. It contains a string message and is timestamped
 * with the instant at which its constructor was called. 
 * @author nick
 */
public class Post {
	
	/**
	 * Create a new post, timestamped with the current time.
	 * @param msg the text content of the post
	 */
	public Post(String msg) {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * @return the Instant at which the post was made
	 */
	public Instant getTimestamp() {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * @return the text content of the post
	 */
	public String getMessage() { 
		throw new RuntimeException("not implemented");
	}
}
