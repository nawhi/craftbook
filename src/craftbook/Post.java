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
	 * Create a new Post with the specified message,
	 * timestamped with the current time.
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
	
	public User getAuthor() {
		return author;
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
	public String getText() {
		return text;
	}
	
	/**
	 * Private overridable method which creates the timestamp
	 * for the post.
	 * @return the Instant representing the post's timestamp
	 */
	protected Instant makeTimestamp() {
		return Instant.now();
	}

	@Override
	public int compareTo(Post other) {
		return timestamp.compareTo(other.timestamp);
	}
}
