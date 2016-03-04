package scheduler.app.services.tasks;

import scheduler.app.entries.TaskEntry;

import java.util.List;

public interface TaskService {

    List<TaskEntry> loadAll();

    TaskEntry load(final long taskId);

    TaskEntry save(final TaskEntry taskEntry);

    void delete(final long taskId);
}
