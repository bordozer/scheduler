package scheduler.core.services.scheduler;

import scheduler.core.models.SchedulerTask;

public interface SchedulerTaskExecutionService {

    void execute(SchedulerTask schedulerTask);
}
