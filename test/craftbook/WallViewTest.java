package craftbook;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;

/**
 * Minimal WallView mock with current time fixed to
 * 10 seconds after the epoch.
 * @author nick
 *
 */
class DummyWallView extends WallView {

	public static final Instant TIME = Instant.ofEpochSecond(10);
	
	public DummyWallView(User user) {
		super(user);
	}
	
	@Override 
	public Instant getCurrentTime() {
		return TIME;
	}
}

public class WallViewTest {

	/*
	 * Things to test:
	 * - user following another user with blank timeline
	 * - user following another user
	 * - ordering of posts
	 */
	
	private static final Instant POST_TIME = Instant.ofEpochSecond(10);
	
	private DummyUser dave = new DummyUser("dave");
	private DummyUser dan = new DummyUser("dan");
	private DummyUser bill = new DummyUser("bill");
	
	private DummyWallView view = new DummyWallView(dave);
	
	@Test
	public void emptyProfileGivesEmptyString() {
		assertEquals("", view.calculate());
		dave.follow(dan);
		dave.follow(bill);
		assertEquals("Even with empty followers view should still be empty", 
				"", view.calculate());
	}
	
	@Test
	public void ownPostsArePrintedWithAuthor() {
		dave.post("Hello", POST_TIME);
		assertEquals("dave - Hello (moments ago)", view.calculate());
	}
	
	@Test
	public void othersPostsArePrintedWithAuthor() {
		dave.follow(dan);
		dan.post("Hello", POST_TIME);
		assertEquals("dan - Hello (moments ago)", view.calculate());
	}
	
	@Test
	public void ownPostsAreSortedCorrectly() {
		dave.post("Hello", POST_TIME);
		dave.post("Hello", POST_TIME);
		assertEquals("dave - Hello (moments ago)\ndave - Hello (moments ago)", view.calculate());
		
		dave.post("Hello again", POST_TIME);
		assertEquals(
			"dave - Hello again (moments ago)\n"
			+ "dave - Hello (moments ago)\n"
			+ "dave - Hello (moments ago)", 
			view.calculate()
		);
	}
	
	@Test
	public void mixedPostsAreSortedEarliestFirst() {
		dave.post("Epoch+2", Instant.ofEpochSecond(2));
		bill.post("Epoch+3", Instant.ofEpochSecond(3));
		dan.post("Epoch+4", Instant.ofEpochSecond(4));
		
		dave.follow(bill);
		dave.follow(dan);
		
		assertEquals(
			"dan - Epoch+4 (6 seconds ago)\n"
			+ "bill - Epoch+3 (7 seconds ago)\n"
			+ "dave - Epoch+2 (8 seconds ago)",
			view.calculate()
		);
	}
	
	@Test
	public void timeOfFollowingDoesntInfluenceResult() {
		dave.follow(bill);
		bill.post("Epoch+8", Instant.ofEpochSecond(8));
		assertEquals("bill - Epoch+8 (2 seconds ago)", view.calculate());
		
		dan.post("Epoch+9", Instant.ofEpochSecond(9));
		dave.follow(dan);
		assertEquals(
			"dan - Epoch+9 (1 second ago)\nbill - Epoch+8 (2 seconds ago)", 
			view.calculate()
		);
	}

}
