package craftbook;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ModelTest {

	@Test
	public void canCreateNewUser() {
		Model m = new Model();
		User dave = m.createUser("dave");
		assertSame(dave, m.getUser("dave"));
		assertEquals("dave", m.getUser("dave").getHandle());
	}
	
	@Test
	public void userExistsWhenCreated() {
		Model m = new Model();
		m.createUser("dave");
		assertTrue(m.userExists("dave"));
	}
	
	@Test
	public void getUserReturnsReferenceModifiableInPlace() {
		Model m = new Model();
		User dave = m.createUser("dave");
		dave.post("hello world");
		assertSame(dave, m.getUser("dave"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void cannotGetNonexistentUser() {
		new Model().getUser("nonexistent");
	}
	
	@Test
	public void nonexistentUserDoesNotExist() {
		assertFalse(new Model().userExists("nonexistent"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cannotCreateTwoUsersWithSameHandle() {
		Model m = new Model();
		m.createUser("dave");
		m.createUser("dave");
	}
	
}
