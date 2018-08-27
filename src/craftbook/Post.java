package craftbook;

import java.time.Instant;

/**
 * Immutable data class representing a post made by 
 * a user. It contains a string message and is timestamped
 * with the instant at which its constructor was called. 
 * @author nick
 */
public class Post implements Comparable<Post> {
	
	private final String text;
	private final User author;
	private final Instant timestamp;

	/**
	 * @param msg the text content of the post
	 * @param ts the time at which the post was created
	 * @param u the author of the post
	 */
	public Post(String msg, Instant ts, User u) {
		if (msg.isEmpty())
			throw new IllegalArgumentException("Cannot post an empty message");
		text = msg;
		author = u;
		timestamp = ts;
	}
	
	/**
	 * @return the author of the post
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return the Instant representing the timestamp with 
	 *         which the post was created
	 *         
	 */
	public Instant getTimestamp() {
		return timestamp;
	}
	
	/**
	 * @return the text content of the post
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return the Instant representing the timestamp that
	 *         this post should have
	 */
	protected Instant makeTimestamp() {
		return Instant.now();
	}

	@Override
	public int compareTo(Post other) {
		return timestamp.compareTo(other.timestamp);
	}
}
