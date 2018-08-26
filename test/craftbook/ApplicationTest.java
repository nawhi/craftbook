package craftbook;

import static org.junit.Assert.*;

import org.junit.Test;

import craftbook.Application.EventLoopState;

public class ApplicationTest {

	/*
	 * This test prints lots of stuff to the console
	 */
	
	Application app = new Application();
	
	@Test
	public void continuesOnEmptyInput() {
		assertEquals(EventLoopState.Continue, app.handleInput(""));
	}
	
	@Test
	public void exitsNormallyOnQuitCommand() {
		assertEquals(EventLoopState.Exit, app.handleInput("!quit"));
	}
	
	@Test
	public void continuesOnHelpCommand() {
		assertEquals(EventLoopState.Continue, app.handleInput("!help"));
	}
	
	@Test
	public void continuesOnValidInput() {
		assertEquals(EventLoopState.Continue, app.handleInput("dave -> Hello world"));
	}
	
	@Test
	public void continuesOnInvalidInput() {
		assertEquals(EventLoopState.Continue, app.handleInput("foo FOO zzzz 123"));
	}

}
