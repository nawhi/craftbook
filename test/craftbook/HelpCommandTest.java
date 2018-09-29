package craftbook;

import org.junit.Test;

public class HelpCommandTest {

	@Test
	public void helpTextCanBeOpenedCorrectly() {
		new HelpCommand().execute();
	}

}
