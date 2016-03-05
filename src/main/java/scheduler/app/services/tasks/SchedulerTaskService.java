package scheduler.app.services.tasks;

import org.springframework.transaction.annotation.Transactional;
import scheduler.app.models.SchedulerTask;

import java.util.List;

public interface SchedulerTaskService {

	List<SchedulerTask> loadAll();

	List<SchedulerTask> loadAll(Long userId);

	SchedulerTask load(Long taskId);

	@Transactional
	SchedulerTask add(SchedulerTask schedulerTask);

	@Transactional
	SchedulerTask save(SchedulerTask schedulerTask);

	@Transactional
	void delete(Long taskId);
}
