package schemway.core.models;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RemoteJobTest {

    @Test
    public void twoObjectsShouldBeEqualsIfTheyJHaveTheSameIdsTest() {
        final schemway.core.models.RemoteJob job1 = new schemway.core.models.RemoteJob();
        job1.setId(1L);
        job1.setAuthString("1111");

        final schemway.core.models.RemoteJob job2 = new schemway.core.models.RemoteJob();
        job2.setId(2L);
        job2.setAuthString("2222");

        final schemway.core.models.RemoteJob job3 = new schemway.core.models.RemoteJob();
        job3.setId(1L);
        job3.setAuthString("3333");

        assertFalse(job1.equals(null));
        assertFalse(job1.equals(new schemway.core.models.RemoteJob()));
        assertFalse(job1.equals(job2));
        assertTrue(job1.equals(job3));
    }
}