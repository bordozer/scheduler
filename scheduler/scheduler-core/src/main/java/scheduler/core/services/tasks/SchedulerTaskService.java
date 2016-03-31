package scheduler.core.services.tasks;

import scheduler.core.models.SchedulerTask;

import java.util.List;

public interface SchedulerTaskService {

	List<SchedulerTask> loadAll();

	List<SchedulerTask> loadAll(Long userId);

	SchedulerTask load(Long userId, Long schedulerTaskId);

	SchedulerTask create(Long userId, SchedulerTask schedulerTask);

	SchedulerTask modify(Long userId, SchedulerTask schedulerTask);

	List<scheduler.core.entities.SchedulerTaskEntity> delete(Long userId, Long taskId);
}
