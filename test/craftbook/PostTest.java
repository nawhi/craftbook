package craftbook;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class PostTest {
	
	private User author = new User("dan");

	@Test(expected=IllegalArgumentException.class)
	public void postCannotHaveEmptyMessage() {
		new Post("", Instant.EPOCH, author);
	}
	
	@Test
	public void postsCompareByTimestampEarliestFirst() {
		Post p1 = new Post("foo", Instant.ofEpochSecond(1), author);
		Post p2 = new Post("foo", Instant.ofEpochSecond(2), author);
		Post p3 = new Post("foo", Instant.ofEpochSecond(3), author);
		Post p4 = new Post("foo", Instant.ofEpochSecond(4), author);
		List<Post> posts = Arrays.asList(p2, p1, p4, p3);
		Collections.sort(posts);
		assertSame(p1, posts.get(0));
		assertSame(p2, posts.get(1));
		assertSame(p3, posts.get(2));
		assertSame(p4, posts.get(3));
	}
	

}
