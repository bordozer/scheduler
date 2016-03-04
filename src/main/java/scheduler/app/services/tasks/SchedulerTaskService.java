package scheduler.app.services.tasks;

import scheduler.app.models.SchedulerTask;

import java.util.List;

public interface SchedulerTaskService {

    List<SchedulerTask> loadAll();

    SchedulerTask load(final long taskId);

    SchedulerTask add(final SchedulerTask schedulerTask);

    SchedulerTask save(final SchedulerTask schedulerTask);

    void delete(final long taskId);
}
