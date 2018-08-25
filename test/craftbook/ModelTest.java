package craftbook;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {

	private Model model = new Model();
	private User dave;
	
	@Before
	public void setUp() {
		dave = model.createUser("dave");
	}
	
	@Test
	public void canCreateNewUser() {
		assertSame(dave, model.getUser("dave"));
		assertEquals("dave", model.getUser("dave").getHandle());
	}
	
	@Test
	public void userExistsWhenCreated() {
		assertTrue(model.hasUser("dave"));
	}
	
	@Test
	public void getUserReturnsReferenceModifiableInPlace() {
		dave.post("hello world");
		assertSame(dave, model.getUser("dave"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void cannotGetNonexistentUser() {
		model.getUser("nonexistent");
	}
	
	@Test
	public void nonexistentUserDoesNotExist() {
		assertFalse(model.hasUser("nonexistent"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cannotCreateTwoUsersWithSameHandle() {
		model.createUser("dave");
	}
	
}
