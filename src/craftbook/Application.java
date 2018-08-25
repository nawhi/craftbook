package craftbook;

import java.io.Console;
import java.text.ParseException;
import java.util.Scanner;

public class Application {

	private Model model = new Model();
	private CommandFactory commandFactory = new CommandFactory(model);
	
	private final String PROMPT = ">";
	
	public void runEventLoop() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			for (;;) {
				System.out.print(PROMPT);
				
				String input = scanner.nextLine();
				if (input.equals("!quit")) {
					System.out.println("Bye");
					break;
				}
				
				TokenList tokens;
				try {
					tokens = CommandParser.parse(input);
				} catch (ParseException ex) {
					System.out.println("Error parsing input: " + ex.getMessage());
					continue;
				}
				
				Command command = commandFactory.makeCommand(tokens);
				command.execute();
			}
		} catch (Exception ex) {
			System.err.print("Fatal exception");
			String msg = ex.getMessage();
			if (msg != null)
				System.err.print(": " + msg);
			System.err.println();
		} finally {
			scanner.close();
		}
	}

	public static void main(String[] args) {
		
		// TODO: Handle SIGINT
		
		Application app = new Application();
		app.runEventLoop();
	}
	
	
}
