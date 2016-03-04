package scheduler.app.services.tasks;

import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.models.SchedulerTask;

import java.util.List;

public interface TaskService {

    List<SchedulerTask> loadAll();

    SchedulerTask load(final long taskId);

    SchedulerTask save(final SchedulerTaskEntry schedulerTaskEntry);

    void delete(final long taskId);
}
