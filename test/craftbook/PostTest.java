package craftbook;

import java.time.Instant;

import org.junit.Test;

public class PostTest {

	@Test(expected=IllegalArgumentException.class)
	public void postCannotHaveEmptyMessage() {
		new Post("", Instant.EPOCH);
	}
	

}
