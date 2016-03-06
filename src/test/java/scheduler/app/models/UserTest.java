package scheduler.app.models;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserTest {

	@Test
	public void twoObjectsShouldBeEqualsIfTheyJHaveTheSameIdsTest() {
		final User user1 = new User();
		user1.setId(1L);
		user1.setUsername("User 1");

		final User user2 = new User();
		user2.setId(2L);
		user2.setUsername("User 2");

		final User user3 = new User();
		user3.setId(1L);
		user3.setUsername("User 3");

		assertFalse(user1.equals(null));
		assertFalse(user1.equals(new User()));
		assertFalse(user1.equals(user2));
		assertTrue(user1.equals(user3));
	}
}