package craftbook;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

public class ProfileViewTest {

	private User dave = new User("dave");
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
