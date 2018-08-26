package craftbook;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;

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

	private static final Instant POST_TIME = Instant.ofEpochSecond(10);
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
