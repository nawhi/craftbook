package craftbook;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandFactoryTest {

	
	
	/*
	 * Checks that Command.from() works
	 * as it should.
	 * 
	 * username, ->, message        PostCommand
	 * username, "", ""             ProfileCommand
	 * 
	 * username, follows, username  FollowCommand
	 * username, wall, ""           WallCommand
	 */
	
	@Test
	public void testPostCreation() {
		Command c = CommandFactory.makeCommand("dave", "->", "Hello World");
		assertTrue(c instanceof PostCommand);
	}
	
	@Test
	public void testProfileCreation() {
		Command p = CommandFactory.makeCommand("dave", "", "");
		assertTrue(p instanceof ProfileCommand);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
