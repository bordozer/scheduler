package scheduler.core.services.scheduler;

import org.quartz.Trigger;
import scheduler.core.models.SchedulerTask;

import java.util.List;

public interface SchedulerJobService {

    List<Trigger> buildSchedulerJobTriggers();

    Trigger buildSchedulerJobTrigger(SchedulerTask schedulerTask);
}
