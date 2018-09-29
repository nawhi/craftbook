package craftbook;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class CommandFactoryTest {

	private Model model = new Model();
	private CommandFactory factory = new CommandFactory(model);
	
	/*
	 * username, ->, message        PostCommand
	 * username, "", ""             ProfileCommand
	 * 
	 * username, follows, username  FollowCommand
	 * username, wall, ""           WallCommand
	 */
	
	@Test
	public void nonexistentUserShouldBeCreatedWhenPosting() {
		factory.makeCommand(new TokenList("dave", "->", "Hello World"));
		assertTrue(model.hasUser("dave"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void shouldFailToFollowNonexistentSourceUser() {
		model.createUser("dan");
		factory.makeCommand(new TokenList("nobody", "follows", "dan"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void shouldFailToFollowNonexistentTargetUser() {
		model.createUser("dan");
		factory.makeCommand(new TokenList("dan", "follows", "nobody"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void profileOnNonexistentUserShouldFail() {
		factory.makeCommand(new TokenList("nobody", "", ""));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void wallOnNonexistentUserShouldFail() {
		factory.makeCommand(new TokenList("nobody", "wall", ""));
	}
	
	@Test
	public void existingUserShouldBeGrabbed() {
		User dave1 = model.createUser("dave");
		factory.makeCommand(new TokenList("dave", "->", "Hello World"));
		User dave2 = model.getUser("dave");
		assertSame(dave1, dave2);
	}
	
	@Test
	public void shouldReturnPostCommandWithPostParameters() {
		Command c = factory.makeCommand(new TokenList("dave", "->", "Hello World"));
		assertTrue(c instanceof PostCommand);
	}
	
	@Test
	public void shouldReturnProfileCommandWithProfileParameters() {
		model.createUser("dave");
		Command p = factory.makeCommand(new TokenList("dave", "", ""));
		assertTrue(p instanceof ProfileCommand);
	}
	
	@Test
	public void shouldReturnWallCommandWithWallParameters() {
		model.createUser("dave");
		Command w = factory.makeCommand(new TokenList("dave", "wall", ""));
		assertTrue(w instanceof WallCommand);
	}
	
	@Test
	public void shouldReturnFollowCommandWithFollowParameters() {
		model.createUser("dave");
		model.createUser("dan");
		Command f = factory.makeCommand(new TokenList("dave", "follows", "dan"));
		assertTrue(f instanceof FollowCommand);
	}
	
	@Test
	public void shouldReturnHelpCommandWithHelpParameters() {
		Command h = factory.makeCommand(new TokenList("", "help", ""));
		assertTrue(h instanceof HelpCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidCommandShouldFail() {
		factory.makeCommand(new TokenList("dave", "invalid", ""));
	}
	
}
