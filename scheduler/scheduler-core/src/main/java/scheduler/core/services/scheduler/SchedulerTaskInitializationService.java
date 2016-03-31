package scheduler.core.services.scheduler;

import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.List;

public interface SchedulerTaskInitializationService {

    List<Trigger> buildSchedulerJobTriggers() throws SchedulerException;
}
