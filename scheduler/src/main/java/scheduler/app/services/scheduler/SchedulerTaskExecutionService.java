package scheduler.app.services.scheduler;

import scheduler.app.models.SchedulerTask;

public interface SchedulerTaskExecutionService {

    void execute(SchedulerTask schedulerTask);
}
