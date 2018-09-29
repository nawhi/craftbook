package craftbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HelpCommand implements Command {

	private static String helpText = "";
	private static final String HELPTEXT_FILE = "resources/helptext.txt";
	
	@Override
	public void execute() {
		if (helpText.isEmpty())
			helpText = loadHelpText();
		System.out.println(helpText);
	}
	
	private String loadHelpText() {
		try {
			List<String> lines = Files.readAllLines(Paths.get(HELPTEXT_FILE));
			return String.join("\n", lines);
		} catch (IOException ex) {
			// it's in the source tree, it shouldn't be missing!
			throw new RuntimeException(ex);
		}
	}
	
}
