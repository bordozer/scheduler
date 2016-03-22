package scheduler.app.services.scheduler;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.util.List;

public interface SchedulerTaskInitializationService {

    List<Trigger> initSchedulerTasks() throws SchedulerException;
}
