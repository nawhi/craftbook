package craftbook;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

	protected final String handle;
	protected List<Post> posts = new ArrayList<>();
	protected Set<User> followers = new HashSet<>();
	
	public User(String h) {
		if (h.isEmpty())
			throw new IllegalArgumentException("Handle cannot be the empty string");
		if (h.matches(".*\\s.*"))
			throw new IllegalArgumentException("Handle cannot contain whitespace");
		handle = h;
	}
	
	public String getHandle() {
		return handle;
	}
	
	public List<Post> getPosts() {
		return new ArrayList<>(posts);
	}
	
	public void post(String message) {
		posts.add(new Post(message, Instant.now(), this));
	}
	
	public void follow(User other) {
		if (other == this)
			throw new IllegalArgumentException("User " + handle + " cannot follow itself");
		followers.add(other);
	}
	
	public Set<User> getFollowedUsers() {
		return new HashSet<>(followers);
	}
}
