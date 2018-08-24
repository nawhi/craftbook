package craftbook;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {

	@Test(expected=IllegalArgumentException.class)
	public void postCannotHaveEmptyMessage() {
		new Post("");
	}
	

}
