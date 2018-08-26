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
	 * Find out whether the user with the given handle
	 * already exists or not.
	 * @param handle the handle of the user to find
	 * @return true if the user has previously been
	 * 		   created
	 */
	public boolean hasUser(String handle) {
		return users.containsKey(handle);
	}
	
	/**
	 * Get a user by their handle. Throws an exception
	 * if the user does not exist.
	 * @param handle handle of the user to find
	 * @return reference to the User with the given handle
	 */
	public User getUser(String handle) {
		User u = users.get(handle);
		if (u == null)
			throw new NoSuchElementException("User '" + handle + "' does not exist");
		return u;
	}
	
	/**
	 * Create a new user in the application.
	 * @param handle handle of the new user, which must be
	 * 				 different to the handles of all existing
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
