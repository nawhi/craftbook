package craftbook;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.OutputStream;
import java.time.Instant;

import org.junit.Test;

/**
 * Minimal ProfileView mock with current time fixed to
 * 10 seconds after the epoch.
 * @author nick
 *
 */
class DummyProfileView extends ProfileView {

	public static final Instant TIME = Instant.ofEpochSecond(10);
	
	public DummyProfileView(User user, PrintStream ostream) {
		super(user, ostream);
	}
	
	@Override 
	public Instant getCurrentTime() {
		return TIME;
	}
}

public class ProfileViewTest {

	private static final Instant POST_TIME = Instant.ofEpochSecond(10);
	
	/*
	 * There's scope for this and WallViewTest to test
	 * show() by changing DUMMY_STDOUT to something which captures
	 * and asserts about what's written to it. I didn't know how to 
	 * do this so tested the return value of calculate() instead.
	 */
	private static final PrintStream DUMMY_STDOUT = new PrintStream(new OutputStream() {
		@Override public void write(int b) {}
	});
	private DummyUser dave = new DummyUser("dave");
	private DummyProfileView view = new DummyProfileView(dave, DUMMY_STDOUT);
		
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
