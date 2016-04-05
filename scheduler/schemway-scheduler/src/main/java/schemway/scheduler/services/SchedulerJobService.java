package schemway.scheduler.services;

import schemway.scheduler.models.SchedulerJobTrigger;

import java.util.List;

public interface SchedulerJobService {

    List<SchedulerJobTrigger> buildSchedulerJobTriggers();

    SchedulerJobTrigger buildSchedulerJobTrigger(Long schedulerTaskId);
}
