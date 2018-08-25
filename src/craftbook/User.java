package craftbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class representing a user of the social network. It stores
 * the user's handle, all posts the user has made and all 
 * other users that this user follows.
 * @author nick
 */
public class User {

	private final String handle;
	private List<Post> posts = new ArrayList<>();
	
	/**
	 * Create a new User.
	 * @param h nonempty string of the new user's handle,
	 * 		    which should not contain whitespace
	 */
	public User(String h) {
		if (h.isEmpty())
			throw new IllegalArgumentException("Handle cannot be the empty string");
		if (h.matches(".*\\s.*"))
			throw new IllegalArgumentException("Handle cannot contain whitespace");
		
		handle = h;
	}
	
	/**
	 * @return the user's handle
	 */
	public String getHandle() {
		return handle;
	}
	
	/**
	 * @return a List of all posts the user has made, ordered
	 * 		   chronologically by oldest first
	 */
	public List<Post> getPosts() {
		return new ArrayList<>(posts);
	}
	
	/**
	 * Create a new post, which will be timestamped with
	 * the current time.
	 * @param message nonempty string of the text content of the post
	 */
	public void post(String message) {
		posts.add(Post.from(message));
	}
	
	/**
	 * Record that this user follows another user.
	 * @param other the other User that this user is to follow
	 */
	public void follow(User other) {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * @return a Set containing references to all the users 
	 *         followed by this user
	 */
	public Set<User> getFollowers() {
		throw new RuntimeException("not implemented");
	}
}
