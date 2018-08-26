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
		factory.makeCommand("dave", "follows", "dan");
		assertTrue(model.hasUser("dan"));
	}
	
	@Test
	public void existingUserShouldBeGrabbed() {
		User dave1 = model.createUser("dave");
		factory.makeCommand("dave", "->", "Hello World");
		User dave2 = model.getUser("dave");
		assertSame(dave1, dave2);
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
	
	@Test
	public void shouldReturnWallCommandWithWallParameters() {
		Command w = factory.makeCommand("dave", "wall", "");
		assertTrue(w instanceof WallCommand);
	}
	
	@Test
	public void shouldReturnFollowCommandWithFollowParameters() {
		Command f = factory.makeCommand("dave", "follows", "dan");
		assertTrue(f instanceof FollowCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldFailOnInvalidCommand() {
		factory.makeCommand("dave", "invalid", "");
	}
	
}
