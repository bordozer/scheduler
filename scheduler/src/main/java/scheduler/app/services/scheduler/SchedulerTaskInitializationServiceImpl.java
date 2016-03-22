package scheduler.app.services.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;
import scheduler.app.models.SchedulerTask;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;
import java.util.List;

@Service
public class SchedulerTaskInitializationServiceImpl implements SchedulerTaskInitializationService {

    private static final String CRON = "0 0/1 * * * ?"; // each minute

    @Inject
    private SchedulerTaskService schedulerTaskService;
    ;

    @Override
    public void initSchedulerTasks(Scheduler scheduler) throws SchedulerException {

        scheduler.start();

        List<SchedulerTask> schedulerTasks = schedulerTaskService.loadAll();

        schedulerTasks.stream()
                .forEach(schedulerTask -> {
                    String jobName = getJobName(schedulerTask);
                    String jobGroup = getJobGroup(schedulerTask);
                    String triggerName = getTriggerName(schedulerTask);

                    JobKey jobKey = new JobKey(jobName, jobGroup);
                    JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                            .withIdentity(jobKey)
                            .build();

                    Trigger trigger1 = TriggerBuilder
                            .newTrigger()
                            .withIdentity(triggerName, jobGroup)
                            .withSchedule(CronScheduleBuilder.cronSchedule(CRON))
                            .build();
                    try {
                        scheduler.scheduleJob(job, trigger1);
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                });
    }

    private String getJobGroup(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_JOB_GROUP_%d", schedulerTask.getUser().getId());
    }

    private String getJobName(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_JOB_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId());
    }

    private String getTriggerName(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_TRIGGER_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId());
    }
}
