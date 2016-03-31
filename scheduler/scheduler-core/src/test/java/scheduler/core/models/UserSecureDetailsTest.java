package scheduler.core.models;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserSecureDetailsTest {

    @Test
    public void twoObjectsShouldBeEqualsIfTheyJHaveTheSameIdsTest() {
        final scheduler.core.models.UserSecureDetails details1 = new scheduler.core.models.UserSecureDetails();
        details1.setId(1L);
        details1.setLogin("User 1");

        final scheduler.core.models.UserSecureDetails details2 = new scheduler.core.models.UserSecureDetails();
        details2.setId(2L);
        details2.setLogin("User 2");

        final scheduler.core.models.UserSecureDetails details3 = new scheduler.core.models.UserSecureDetails();
        details3.setId(1L);
        details3.setLogin("User 3");

        assertFalse(details1.equals(null));
        assertFalse(details1.equals(new scheduler.core.models.UserSecureDetails()));
        assertFalse(details1.equals(details2));
        assertTrue(details1.equals(details3));
    }
}