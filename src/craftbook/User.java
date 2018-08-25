package craftbook;

import java.util.ArrayList;
import java.util.HashSet;
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
	private Set<User> followers = new HashSet<>();
	private PostFactory postFactory;
	
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
		
		// Factory just wraps Post constructor by default 
		postFactory = new PostFactory() {
			@Override public Post createPost(String msg) {
				return new Post(msg);
			}
		};
	}
	
	/**
	 * Create a new User, specifying a custom way of creating
	 * that user's posts.
	 * @param h nonempty string of the new user's handle, which
	 *          should not contain whitespace
	 * @param p the PostFactory to use for creating posts
	 */
	public User (String h, PostFactory p) {
		this(h);
		postFactory = p;
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
		posts.add(postFactory.createPost(message));
	}
	
	/**
	 * Record that this user follows another user.
	 * @param other the other User that this user is to follow
	 */
	public void follow(User other) {
		if (other == this)
			throw new IllegalArgumentException("User " + handle + " cannot follow itself");
		followers.add(other);
	}
	
	/**
	 * @return a Set containing references to all the users 
	 *         followed by this user
	 */
	public Set<User> getFollowers() {
		return new HashSet<>(followers);
	}
}
