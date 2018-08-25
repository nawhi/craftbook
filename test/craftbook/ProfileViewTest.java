package craftbook;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

/**
 * Implementation of DummyPostFactory allowing test code
 * to control how posts generated by calls to User.post()
 * are timestamped.
 * 
 * @author nick
 *
 */
class DummyPostFactory implements PostFactory {
	private Instant timestamp = Instant.EPOCH;
	
	@Override 
	public Post createPost(String msg) {
		return DummyPost.create(msg, timestamp);
	}
	
	public void setTimestamp(Instant ts) {
		timestamp = ts;
	}
}

public class ProfileViewTest {

	private User dave = new User("dave", new DummyPostFactory());
	private ProfileView view = new ProfileView(dave); 
	
	/*
	 * Need to test:
	 * - works with empty profiles
	 * - prints correct stuff
	 * - prints in correct order
	 */
	
	@Test
	public void emptyProfileGivesEmptyString() {
		fail("TODO");
	}
	
	@Test
	public void postsArePrintedInCorrectOrder() {
		fail("TODO");
	}
	
}
