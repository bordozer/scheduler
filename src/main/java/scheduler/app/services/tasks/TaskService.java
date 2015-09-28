package scheduler.app.services.tasks;

import scheduler.app.models.TaskEntry;

import java.util.List;

public interface TaskService {

	List<TaskEntry> loadAll();

	TaskEntry load( final long taskId );

	void delete( final long taskId );
}
