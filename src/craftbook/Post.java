package craftbook;

import java.time.Instant;

public class Post implements Comparable<Post> {
	
	private final String text;
	private final User author;
	private final Instant timestamp;

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

	public Instant getTimestamp() {
		return timestamp;
	}
	
	public String getText() {
		return text;
	}
	
	protected Instant makeTimestamp() {
		return Instant.now();
	}

	@Override
	public int compareTo(Post other) {
		return timestamp.compareTo(other.timestamp);
	}
}
