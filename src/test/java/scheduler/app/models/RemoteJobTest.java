package scheduler.app.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoteJobTest {

	@Test
	public void twoObjectsShouldBeEqualsIfTheyJHaveTheSameIdsTest() {
		final RemoteJob job1 = new RemoteJob();
		job1.setId(1L);
		job1.setAuthString("1111");

		final RemoteJob job2 = new RemoteJob();
		job2.setId(2L);
		job2.setAuthString("2222");

		final RemoteJob job3 = new RemoteJob();
		job3.setId(1L);
		job3.setAuthString("3333");

		assertFalse(job1.equals(null));
		assertFalse(job1.equals(new RemoteJob()));
		assertFalse(job1.equals(job2));
		assertTrue(job1.equals(job3));
	}
}