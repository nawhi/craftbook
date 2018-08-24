package craftbook;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

/*
 * Current Status:
 * Getting a mock Post together with control over the
 * timestamp it's created with is proving a giant pain
 */

/*
 * A mock Post which allows control over the timestamp
 * that the post is created with for testing purposes.
 */
//class DummyPost extends Post {
//	private static Instant nextTimestamp = Instant.EPOCH;
//	
//	public DummyPost(String msg) { 
//		super(msg);
//	}
//	
//	/**
//	 * Set the timestamp for future instances of this class
//	 * to have. 
//	 * 
//	 * @param i the Instant to use as timestamps for
//	 * 		    future instances of this class. This must be
//	 * 			equal to or after the current value, to
//	 * 			avoid breaking the User invariant that the
//	 * 			order in which post() was called is also the
//	 * 			chronological order of the posts.
//	 */
//	public static void setNextTimestamp(Instant i) {
//		if (i.isBefore(nextTimestamp))
//			throw new IllegalArgumentException("i must be after the current timestamp");
//		nextTimestamp = i;
//	}
//	
//	@Override
//	protected Instant getTimestamp() {
//		return nextTimestamp;
//	}
//}

public class ProfileViewTest {

//	private User dave = new User("dave");
//	private ProfileView view = new ProfileView(dave); 
	
	@Before
	public void setUp() throws Exception {
		// TODO How to actually ensure the test version of Post
		// gets created?
		// in dynamic languages you could just replace the method,
		// don't think that's possible in Java
	}
	
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
