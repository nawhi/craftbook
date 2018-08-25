package craftbook;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;

/**
 * Minimal User mock which allows control over the
 * timestamp of posts.
 * Tests must be careful not to supply timestamps that
 * are not in chronological order to sequential posts, 
 * as this would break the postcondition of getPosts().
 * 
 * @author nick
 *
 */
class DummyUser extends User {
	
	public DummyUser(String handle) {
		super(handle);
	}
	
	/**
	 * Add a new post with timestamp control.
	 * NB this is an overload not override, 
	 * void post(String) is still available!
	 * @param message the text content of the post 
	 * @param timestamp time the post was created
	 */
	public void post(String message, Instant timestamp) {
		posts.add(new Post(message, timestamp));
	}
}

/**
 * Minimal ProfileView mock which allows control over the
 * view's current time.
 * @author nick
 *
 */
class DummyProfileView extends ProfileView {

	public DummyProfileView(User user) {
		super(user);
	}
	
	@Override 
	public Instant getCurrentTime() {
		return Instant.ofEpochSecond(10);
	}
}

public class ProfileViewTest {

	private final Instant POST_TIME = Instant.ofEpochSecond(10);
	private DummyUser dave = new DummyUser("dave");
	private DummyProfileView view = new DummyProfileView(dave);
		
	
	/*
	 * Need to test:
	 * - works with empty profiles
	 * - prints correct stuff
	 * - prints in correct order
	 */
	
	@Test
	public void emptyProfileGivesEmptyString() {
		assertEquals("", view.calculate());
	}
	
	@Test
	public void onePostIsPrintedCorrectly() {
		dave.post("Hello World", POST_TIME);
		assertEquals("Hello World (moments ago)", view.calculate());
	}
	
	@Test
	public void postsArePrintedSeparatedByNewlineOnly() {
		dave.post("Hello", POST_TIME);
		dave.post("Hello", POST_TIME);
		
		assertEquals("Hello (moments ago)\nHello (moments ago)", view.calculate());
		
		dave.post("Hello again", POST_TIME);
		
		assertEquals(
			"Hello again (moments ago)\nHello (moments ago)\nHello (moments ago)", 
			view.calculate());
	}
	
	@Test
	public void whitespaceInMessageIsPreserved() {
		String spaced = "   He ll o   ";
		dave.post(spaced, POST_TIME);
		assertEquals(spaced + " (moments ago)", view.calculate());
	}
	
}
