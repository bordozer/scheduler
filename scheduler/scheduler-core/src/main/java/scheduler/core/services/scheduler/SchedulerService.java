package scheduler.core.services.scheduler;

import org.quartz.SchedulerException;

public interface SchedulerService {

    void start() throws SchedulerException;

    void stop();

    void scheduleTask(Long scheduleTaskId);

    void unScheduleTask(Long scheduleTaskId);
}
