package schemway.core.models;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserTest {

    @Test
    public void twoObjectsShouldBeEqualsIfTheyJHaveTheSameIdsTest() {
        final schemway.core.models.User user1 = new schemway.core.models.User();
        user1.setId(1L);
        user1.setUsername("User 1");

        final schemway.core.models.User user2 = new schemway.core.models.User();
        user2.setId(2L);
        user2.setUsername("User 2");

        final schemway.core.models.User user3 = new schemway.core.models.User();
        user3.setId(1L);
        user3.setUsername("User 3");

        assertFalse(user1.equals(null));
        assertFalse(user1.equals(new schemway.core.models.User()));
        assertFalse(user1.equals(user2));
        assertTrue(user1.equals(user3));
    }
}