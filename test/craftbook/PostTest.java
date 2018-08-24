package craftbook;

import org.junit.Test;

public class PostTest {

	@Test(expected=IllegalArgumentException.class)
	public void postCannotHaveEmptyMessage() {
		new Post("");
	}
	

}
