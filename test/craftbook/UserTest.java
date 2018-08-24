package craftbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserTest {

	@Test(expected=IllegalArgumentException.class)
	public void userCannotHaveEmptyHandle() {
		new User("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void userCannotHaveHandleWithSpaces() {
		new User("d a v e");
	}
	
	@Test
	public void canGetUserHandle() {
		User dave = new User("dave");
		assertEquals("dave", dave.getHandle());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCannotPostEmptyString() {
		new User("dave").post("");
	}
	
	@Test
	public void getPostsNoPostsReturnsEmptyList() {
		User dave = new User("dave");
		List<Post> posts = dave.getPosts();
		assertTrue(posts.isEmpty());
	}
	
	@Test
	public void getPostsOnePostReturnsPost() {
		User dave = new User("dave");
		dave.post("hello world");
		assertEquals("hello world", dave.getPosts().get(0).getMessage());
	}
	
	@Test
	public void postsNotModifiableExternally() {
		User dave = new User("##?");
		dave.post("foo");
		dave.post("bar");
		List<Post> unmodified = dave.getPosts();
		List<Post> modified = dave.getPosts();
		modified.remove(0); // shouldn't change dave
		assertEquals(unmodified, dave.getPosts());
	}
	
	@Test
	public void getPostsReturnsCorrectOrder() {
		User dave = new User("dave");
		dave.post("1");
		dave.post("2");
		List<Post> posts = dave.getPosts();
		assertEquals("1", posts.get(0).getMessage());
		assertEquals("2", posts.get(1).getMessage());
	}
	
	@Test
	public void canPostSameMessageTwice() {
		User dave = new User("dave");
		String msg = "_%?!é";
		dave.post(msg);
		dave.post(msg);
		List<Post> posts = dave.getPosts();
		assertEquals(msg, posts.get(0).getMessage());
		assertEquals(msg, posts.get(1).getMessage());
	}

}
