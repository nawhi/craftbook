package craftbook;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The model of the application. This singleton keeps track
 * of runtime data (i.e. a list of all users that
 * have been created), allowing access to that data,
 * and also allowing new users to be created.
 * @author nick
 *
 */
public class Model {
	
	private Map<String, User> users = new HashMap<>();
	
	/**
	 * @param handle the handle of the user to find
	 * @return true if a user with this handle exists,
	 * 		   false otherwise
	 */
	public boolean hasUser(String handle) {
		return users.containsKey(handle);
	}
	
	/**
	 * Get a pre-existing user by their handle.
	 * @param handle the handle of the user to find
	 * @return reference to the User object with the given handle
	 */
	public User getUser(String handle) {
		User u = users.get(handle);
		if (u == null)
			throw new NoSuchElementException("User '" + handle + "' does not exist");
		return u;
	}
	
	/**
	 * @param handle handle of the new user, which must be
	 * 				 different to the handles of all other
	 * 				 users
	 * @return reference to the User object that was created
	 */
	public User createUser(String handle) {
		if (hasUser(handle))
			throw new IllegalArgumentException("User '" + handle + "' already exists");
		User created = new User(handle);
		users.put(handle, created);
		return created;
	}
	
}
