package schemway.scheduler.services;

import schemway.scheduler.models.ScheduledTask;

import java.util.List;

public interface SchedulerJobService {

    List<ScheduledTask> buildScheduledTasks();

    ScheduledTask buildScheduledTask(Long schedulerTaskId);
}
