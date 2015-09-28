package scheduler.app.services.tasks;

import scheduler.app.models.TaskEntry;

import java.util.List;

public interface TaskEntryService {

	List<TaskEntry> loadAll();

	TaskEntry load( final int taskId );

	TaskEntry delete( final int taskId );
}
