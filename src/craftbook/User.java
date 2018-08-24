package craftbook;

import java.util.List;

/**
 * Class representing a user of the social network. It stores
 * the user's handle, all posts the user has made and all 
 * other users that this user follows.
 * @author nick
 */
public class User {

	/**
	 * Create a new User.
	 * @param h nonempty string of the new user's handle,
	 * 		    which should not contain whitespace
	 */
	public User(String h) {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * @return a List of all posts the user has made, ordered
	 * 		   chronologically by oldest first
	 */
	public List<Post> getPosts() {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * Create a new post, which will be timestamped with
	 * the current time.
	 * @param message nonempty string of the text content of the post
	 */
	public void post(String message) {
		throw new RuntimeException("not implemented");
	}
}
