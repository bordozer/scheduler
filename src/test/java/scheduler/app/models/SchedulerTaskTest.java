package scheduler.app.models;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SchedulerTaskTest {

	@Test
	public void twoObjectsShouldBeEqualsIfTheyJHaveTheSameIdsTest() {
		final SchedulerTask task1 = new SchedulerTask();
		task1.setId(1L);
		task1.setTaskName("Task 1");

		final SchedulerTask task2 = new SchedulerTask();
		task2.setId(2L);
		task2.setTaskName("Task 2");

		final SchedulerTask task3 = new SchedulerTask();
		task3.setId(1L);
		task3.setTaskName("Task 3");

		assertFalse(task1.equals(null));
		assertFalse(task1.equals(new SchedulerTask()));
		assertFalse(task1.equals(task2));
		assertTrue(task1.equals(task3));
	}
}