package scheduler.app.services.tasks;

import scheduler.app.entities.SchedulerTaskEntity;
import scheduler.app.models.SchedulerTask;

import java.util.List;

public interface SchedulerTaskService {

	List<SchedulerTask> loadAll(Long userId);

	SchedulerTask load(Long userId, Long schedulerTaskId);

	SchedulerTask create(Long userId, SchedulerTask schedulerTask);

	SchedulerTask modify(Long userId, SchedulerTask schedulerTask);

	List<SchedulerTaskEntity> delete(Long userId, Long taskId);
}
