package schemway.scheduler.services;

import schemway.scheduler.models.SchedulerJobModel;

import java.util.List;

public interface SchedulerJobService {

    List<SchedulerJobModel> buildScheduledTasks();

    SchedulerJobModel buildScheduledTask(Long schedulerTaskId);
}
