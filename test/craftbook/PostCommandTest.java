package craftbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PostCommandTest {

	/*
	 * check that the post command successfully creates
	 * the right post in the user
	 * (NB: we're not testing User.post() here, so it's
	 * quite a short one)
	 */
	
	private User dave = new User("dave");
	private PostCommand command = new PostCommand(dave, "Hello World");
	
	@Test
	public void executeSuccessfullyCreatesPost() {
		command.execute();
		List<Post> posts = dave.getPosts();
		assertEquals(1, posts.size());
		assertEquals("Hello World", posts.get(0).getMessage());
	}

}
