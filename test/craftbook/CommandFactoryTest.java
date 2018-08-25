package craftbook;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandFactoryTest {

	private Model model = new Model();
	private CommandFactory factory = new CommandFactory(model);
	
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
	public void nonexistentUserShouldBeCreated() {
		factory.makeCommand("dave", "", "");
		assertTrue(model.hasUser("dave"));
	}
	
	@Test
	public void shouldReturnPostCommandWithPostParameters() {
		Command c = factory.makeCommand("dave", "->", "Hello World");
		assertTrue(c instanceof PostCommand);
	}
	
	@Test
	public void shouldReturnProfileCommandWithProfileParameters() {
		Command p = factory.makeCommand("dave", "", "");
		assertTrue(p instanceof ProfileCommand);
	}
	
}
