package craftbook;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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
	public void nonexistentUserShouldBeCreatedWhenPosting() {
		factory.makeCommand("dave", "->", "Hello World");
		assertTrue(model.hasUser("dave"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void shouldFailToFollowNonexistentSourceUser() {
		model.createUser("dan");
		factory.makeCommand("nobody", "follows", "dan");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void shouldFailToFollowNonexistentTargetUser() {
		model.createUser("dan");
		factory.makeCommand("dan", "follows", "nobody");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void profileOnNonexistentUserShouldFail() {
		factory.makeCommand("nobody", "", "");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void wallOnNonexistentUserShouldFail() {
		factory.makeCommand("nobody", "wall", "");
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
		model.createUser("dave");
		Command p = factory.makeCommand("dave", "", "");
		assertTrue(p instanceof ProfileCommand);
	}
	
	@Test
	public void shouldReturnWallCommandWithWallParameters() {
		model.createUser("dave");
		Command w = factory.makeCommand("dave", "wall", "");
		assertTrue(w instanceof WallCommand);
	}
	
	@Test
	public void shouldReturnFollowCommandWithFollowParameters() {
		model.createUser("dave");
		model.createUser("dan");
		Command f = factory.makeCommand("dave", "follows", "dan");
		assertTrue(f instanceof FollowCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidCommandShouldFail() {
		factory.makeCommand("dave", "invalid", "");
	}
	
}
