package scheduler.scheduler.services;

import org.quartz.Trigger;

import java.util.List;

public interface SchedulerJobService {

    List<Trigger> buildSchedulerJobTriggers();

    Trigger buildSchedulerJobTrigger(Long schedulerTaskId);
}
