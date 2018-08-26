package craftbook;

import static org.junit.Assert.*;

import org.junit.Test;

public class FollowCommandTest {

	/*
	 * Just a simple sanity check that follow does what
	 * it should, since most other test cases are handled
	 * lower down
	 */
	
	private User dave = new User("dave");
	private User dan = new User("dan");
	private FollowCommand command = new FollowCommand(dave, dan);
	
	@Test
	public void followCommandSuccessfullyAddsFollower() {
		command.execute();
		assertEquals(1, dave.getFollowers().size());
		assertTrue(dave.getFollowers().contains(dan));
	}

}
