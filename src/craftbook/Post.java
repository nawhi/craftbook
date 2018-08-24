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
	

	protected Post(String msg) {
		if (msg.isEmpty())
			throw new IllegalArgumentException("Cannot post an empty message");
		message = msg;
		timestamp = getTimestamp();
	}

	/**
	 * Create a new post, timestamped with the current time.
	 * @param msg the text content of the post
	 */
	public static Post from(String msg) {
		return new Post(msg);
	}
	
	/**
	 * @return the Instant to use as the timestamp for the post
	 */
	protected Instant getTimestamp() {
		return Instant.now();
	}
	
	/**
	 * @return the text content of the post
	 */
	public String getMessage() { 
		return message;
	}
}
