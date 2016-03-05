package scheduler.app.services.tasks;

import org.springframework.transaction.annotation.Transactional;
import scheduler.app.models.SchedulerTask;

import java.util.List;

public interface SchedulerTaskService {

	List<SchedulerTask> loadAll();

	SchedulerTask load(final long taskId);

	@Transactional
	SchedulerTask add(final SchedulerTask schedulerTask);

	@Transactional
	SchedulerTask save(final SchedulerTask schedulerTask);

	@Transactional
	void delete(final long taskId);
}
