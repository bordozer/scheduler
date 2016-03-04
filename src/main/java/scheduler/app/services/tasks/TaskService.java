package scheduler.app.services.tasks;

import scheduler.app.entries.SchedulerTaskEntry;

import java.util.List;

public interface TaskService {

    List<SchedulerTaskEntry> loadAll();

    SchedulerTaskEntry load(final long taskId);

    SchedulerTaskEntry save(final SchedulerTaskEntry schedulerTaskEntry);

    void delete(final long taskId);
}
