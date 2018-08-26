package craftbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Application {
	
	private enum EventLoopState {
		EXIT_NORMAL,
		EXIT_ERROR,
		CONTINUE
	}

	private static final String HELPTEXT_FILE = "helptext.txt";
	private static final String PROMPT = "> ";
	
	private static String helpText = "";
	
	private Model model = new Model();
	private CommandFactory commandFactory = new CommandFactory(model);
	
	public int runEventLoop() {
		Scanner scanner = new Scanner(System.in);
		
		printWelcomeMessage();
			
		for (;;) {
			System.out.print(PROMPT);
			
			String input = scanner.nextLine();
			
			switch(handleInput(input))
			{
			case EXIT_NORMAL:
				scanner.close();
				return 0;
			case EXIT_ERROR:
				scanner.close();
				return 1;
			case CONTINUE:
				break;
			}
		}
	}
	
	private EventLoopState handleInput(String input) {
		switch(input)
		{
		case "":
			// Ignore empty inputs
			return EventLoopState.CONTINUE;
		case "!quit":
			System.out.println("Bye");
			return EventLoopState.EXIT_NORMAL;
		case "!help":
			printHelp();
			return EventLoopState.CONTINUE;
		}
		
		TokenList tokens;
		Command command;
		try { 
			tokens = CommandParser.parse(input);
			command = commandFactory.makeCommand(tokens);
		} catch (Exception ex) {
			// Don't let these crash the program - they may be user error
			if (ex.getMessage() != null)
				System.err.println("Couldn't parse input:" + ex.getMessage());
			else
				System.err.println("Unexpected " + ex.getClass().getName() + " encountered");
			return EventLoopState.CONTINUE;
		}
		
		try {
			command.execute();
		} catch (Exception ex) {
			// Treat errors in execution as unrecoverable
			dumpErrorDetails(ex);
			return EventLoopState.EXIT_ERROR;
		}
		
		return EventLoopState.CONTINUE;
	}
	
	private static void printWelcomeMessage() {
		System.out.println("Welcome to CraftBook");
		System.out.println("Type !help for help");
		System.out.println("Type !quit to quit");
	}
	
	private static void printHelp() {
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
		
		// TODO: Handle SIGINT
		
		Application app = new Application();
		int code = app.runEventLoop();
		System.exit(code);
	}
	
	
}
