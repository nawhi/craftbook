package craftbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserTest {
	
	private User dave = new User("dave");

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
		assertEquals("dave", dave.getHandle());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCannotPostEmptyString() {
		dave.post("");
	}
	
	@Test
	public void getPostsNoPostsReturnsEmptyList() {
		List<Post> posts = dave.getPosts();
		assertTrue(posts.isEmpty());
	}
	
	@Test
	public void getPostsOnePostReturnsPost() {
		dave.post("hello world");
		assertEquals("hello world", dave.getPosts().get(0).getMessage());
	}
	
	@Test
	public void postsNotModifiableExternally() {
		dave.post("foo");
		dave.post("bar");
		List<Post> unmodified = dave.getPosts();
		List<Post> modified = dave.getPosts();
		modified.remove(0); // shouldn't change dave
		assertEquals(unmodified, dave.getPosts());
	}
	
	@Test
	public void getPostsReturnsCorrectOrder() {
		dave.post("1");
		dave.post("2");
		List<Post> posts = dave.getPosts();
		assertEquals("1", posts.get(0).getMessage());
		assertEquals("2", posts.get(1).getMessage());
	}
	
	@Test
	public void canPostSameMessageTwice() {
		String msg = "_%?!é";
		dave.post(msg);
		dave.post(msg);
		List<Post> posts = dave.getPosts();
		assertEquals(msg, posts.get(0).getMessage());
		assertEquals(msg, posts.get(1).getMessage());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void userCannotFollowItself() {
		dave.follow(dave);
	}
	
	@Test
	public void getFollowersNoFollowersReturnsEmptySet() {
		assertTrue(dave.getFollowers().isEmpty());
	}
	
	@Test
	public void getFollowersOneFollowerReturnsFollower() {
		User dan = new User("dan");
		dave.follow(dan);
		assertEquals(1, dave.getFollowers().size());
		assertTrue(dave.getFollowers().contains(dan));
	}
	
	@Test
	public void getFollowersMultipleFollowersReturnsAllFollowers() {
		User dan = new User("dan");
		User adam = new User("adam");
		dave.follow(dan);
		dave.follow(adam);
		assertEquals(2, dave.getFollowers().size());
		assertTrue(dave.getFollowers().contains(dan));
		assertTrue(dave.getFollowers().contains(adam));
	}
	
	@Test
	public void mutualFollowingWorks() {
		User dan = new User("dan");
		dave.follow(dan);
		dan.follow(dave);
		assertEquals(1, dave.getFollowers().size());
		assertTrue(dave.getFollowers().contains(dan));
		assertEquals(1, dan.getFollowers().size());
		assertTrue(dan.getFollowers().contains(dave));
	}
}
