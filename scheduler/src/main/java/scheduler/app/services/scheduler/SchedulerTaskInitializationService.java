package scheduler.app.services.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public interface SchedulerTaskInitializationService {

    void initSchedulerTasks(Scheduler scheduler) throws SchedulerException;
}
