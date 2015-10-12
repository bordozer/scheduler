package scheduler.app.services.tasks;

import scheduler.app.models.TaskEntry;

import java.util.List;

public interface TaskService {

	List<TaskEntry> loadAll();

	TaskEntry load( final long taskId );

	TaskEntry save( final TaskEntry taskEntry );

	void delete( final long taskId );
}
