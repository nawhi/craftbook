package craftbook;

import java.time.Instant;

/**
 * Immutable data class representing a post made by 
 * a user. It contains a string message and is timestamped
 * with the instant at which its constructor was called. 
 * @author nick
 */
public class Post {
	
	private final String message;
	private final Instant timestamp;
	
	/**
	 * Create a new post, timestamped with the current time.
	 * @param msg the text content of the post
	 */
	public Post(String msg) {
		if (msg.isEmpty())
			throw new IllegalArgumentException("Cannot post an empty message");
		message = msg;
		timestamp = Instant.now();
	}
	
	/**
	 * @return the Instant at which the post was made
	 */
	public Instant getTimestamp() {
		return timestamp;
	}
	
	/**
	 * @return the text content of the post
	 */
	public String getMessage() { 
		return message;
	}
}
