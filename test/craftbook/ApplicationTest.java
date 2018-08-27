package craftbook;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import craftbook.Application.EventLoopState;

public class ApplicationTest {

	private PrintStream stdout;
	
	@Before
	public void setUp() {
		/*
		 * Reroute unnecessary output during 
		 * these tests to a dummy which discards it
		 */
		stdout = System.out;
		System.setOut(new PrintStream(new OutputStream() {
			@Override public void write(int bytes) {}
		}));
	}
	
	@After
	public void tearDown() {
		// Restore trashed standard out
		System.setOut(stdout);
	}
	
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
	
	@Test
	public void helpTextFileCanBeOpenedCorrectly() {
		Application.printHelp();
	}

}
