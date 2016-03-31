package scheduler.core.services.scheduler;

import org.quartz.SchedulerException;

public interface SchedulerService {

    void startScheduler() throws SchedulerException;

    void stopScheduler();

    void scheduleTask(Long scheduleTaskId);

    void unScheduleTask(Long scheduleTaskId);

    void unscheduleTasks() throws SchedulerException;

    void scheduleTasks();

    boolean isRunning();
}
