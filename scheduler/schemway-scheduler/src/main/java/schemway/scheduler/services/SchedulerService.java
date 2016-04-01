package schemway.scheduler.services;

import org.quartz.SchedulerException;

public interface SchedulerService {

    void startScheduler() throws SchedulerException;

    void stopScheduler();

    void scheduleTask(Long scheduleTaskId);

    void unScheduleTask(Long scheduleTaskId);

    void unscheduleAllTasks() throws SchedulerException;

    void scheduleAllTasks();

    boolean isRunning();
}
