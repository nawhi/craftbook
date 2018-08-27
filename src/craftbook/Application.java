package craftbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Application class containing the main entry point
 * and event loop for the program.
 * @author nick
 *
 */
public class Application {
	
	public enum EventLoopState {
		Continue,
		Exit
	}
	
	private static final String HELPTEXT_FILE = "resources/helptext.txt";
	private static final String PROMPT = "> ";
	
	private static String helpText = "";
	
	private Model model = new Model();
	private CommandFactory commandFactory = new CommandFactory(model);
	
	public int runEventLoop() {
		printWelcomeMessage();
			
		try (Scanner scanner = new Scanner(System.in)) {
			for (;;) {
				System.out.print(PROMPT);

				String input = scanner.nextLine();
				EventLoopState state = handleInput(input);
				if (state == EventLoopState.Exit)
					return 0;
			}
		} catch (Exception ex) {
			dumpErrorDetails(ex);
			return 1;
		}
	}
	
	/**
	 * @param input a line of input from the user
	 * @return an EventLoopState indicating what the
	 *         outcome of the input was for the program
	 */
	public EventLoopState handleInput(String input) {
		switch(input)
		{
		case "":
			// Ignore empty inputs
			return EventLoopState.Continue;
		case "!quit":
			System.out.println("Bye");
			return EventLoopState.Exit;
		case "!help":
			printHelp();
			return EventLoopState.Continue;
		}
		
		TokenList tokens;
		Command command;
		try { 
			tokens = CommandParser.parse(input);
			command = commandFactory.makeCommand(tokens);
		} catch (Exception ex) {
			// Don't let these crash the program - they may be user error
			if (ex.getMessage() != null)
				System.out.println(ex.getMessage());
			else
				System.out.println("Unexpected " + ex.getClass().getName() + " encountered while parsing input");
			return EventLoopState.Continue;
		}
		
		// Errors in execution are fatal, so not caught here
		command.execute();
		return EventLoopState.Continue;
	}
	
	private static void printWelcomeMessage() {
		System.out.println("Welcome to CraftBook");
		System.out.println("Type !help for help");
		System.out.println("Type !quit to quit");
	}
	
	public static void printHelp() {
		if (helpText.isEmpty()) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(HELPTEXT_FILE));
				helpText = String.join("\n", lines);
			} catch (IOException ex) {
				// it's in the source tree, it shouldn't be missing!
				throw new RuntimeException(ex);
			}			
		}
		System.out.println(helpText);
	}
	
	private static void dumpErrorDetails(Exception ex) {
		System.err.println("An unrecoverable error was experienced");
		System.err.println("Error type: " + ex.getClass().getName());
		if (ex.getMessage() != null)
			System.err.println("Reason: " + ex.getMessage());
		System.err.println("Backtrace: ");
		ex.printStackTrace();
	}

	public static void main(String[] args) {
		Application app = new Application();
		int code = app.runEventLoop();
		System.exit(code);
	}
	
	
}
