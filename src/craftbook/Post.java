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
	 * Create a new Post with the specified message,
	 * timestamped with the current time.
	 * @param msg the text content of the post
	 * @param ts the time at which the post was created
	 */
	public Post(String msg, Instant ts) {
		if (msg.isEmpty())
			throw new IllegalArgumentException("Cannot post an empty message");
		message = msg;
		timestamp = ts;
	}

	/**
	 * @return the Instant representing the post's timestamp 
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
	
	/**
	 * Private overridable method which creates the timestamp
	 * for the post.
	 * @return the Instant representing the post's timestamp
	 */
	protected Instant makeTimestamp() {
		return Instant.now();
	}
}
