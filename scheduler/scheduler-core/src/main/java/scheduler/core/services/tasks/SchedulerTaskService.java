package scheduler.core.services.tasks;

import scheduler.core.entities.SchedulerTaskEntity;
import scheduler.core.models.SchedulerTask;

import java.util.List;

public interface SchedulerTaskService {

	List<SchedulerTask> loadAll();

	List<SchedulerTask> loadAll(Long userId);

	SchedulerTask load(Long schedulerTaskId);

	SchedulerTask load(Long userId, Long schedulerTaskId);

	SchedulerTask create(Long userId, SchedulerTask schedulerTask);

	SchedulerTask modify(Long userId, SchedulerTask schedulerTask);

	List<SchedulerTaskEntity> delete(Long userId, Long taskId);
}
