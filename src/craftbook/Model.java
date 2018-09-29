package craftbook;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Model {
	
	private Map<String, User> users = new HashMap<>();
	
	public boolean hasUser(String handle) {
		return users.containsKey(handle);
	}
	
	public User getUser(String handle) {
		User u = users.get(handle);
		if (u == null)
			throw new NoSuchElementException("User '" + handle + "' does not exist");
		return u;
	}
	
	public User createUser(String handle) {
		if (hasUser(handle))
			throw new IllegalArgumentException("User '" + handle + "' already exists");
		User created = new User(handle);
		users.put(handle, created);
		return created;
	}
	
}
