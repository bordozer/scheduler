package scheduler.app.services.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;
import scheduler.app.models.SchedulerTask;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskInitializationServiceImpl implements SchedulerTaskInitializationService {

    private static final String CRON = "0 0/1 * * * ?"; // each minute

    @Inject
    private SchedulerTaskService schedulerTaskService;
    ;

    @Override
    public List<Trigger> initSchedulerTasks() throws SchedulerException {

        List<SchedulerTask> schedulerTasks = schedulerTaskService.loadAll();

        return schedulerTasks.stream()
                .map(schedulerTask -> {
                    String jobName = getJobName(schedulerTask);
                    String jobGroup = getJobGroup(schedulerTask);

                    JobKey jobKey = new JobKey(jobName, jobGroup);
                    JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                            .withIdentity(jobKey)
                            .build();

                    /*Trigger trigger1 = TriggerBuilder
                            .newTrigger()
                            .withIdentity(getTriggerName(schedulerTask), jobGroup)
                            .withSchedule(CronScheduleBuilder.cronSchedule(CRON))
                            .build();*/

                    return cronTriggerFactoryBean(job, jobName, jobGroup, CRON).getObject();

                }).collect(Collectors.toList());
    }

    public CronTriggerFactoryBean cronTriggerFactoryBean(final JobDetail job, final String jobName, final String jobGroup, final String cron) {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setName(jobName);
        stFactory.setGroup(jobGroup);
        stFactory.setJobDetail(job);
        stFactory.setCronExpression(cron);
        return stFactory;
    }

    /*public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(final SchedulerTask schedulerTask) {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("pickupsAlertFacadeImpl");
        obj.setTargetMethod("sendPickupAlertEmails");
        return obj;
    }*/

    private String getJobGroup(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_JOB_GROUP_%d", schedulerTask.getUser().getId());
    }

    private String getJobName(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_JOB_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId());
    }

    /*private String getTriggerName(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_TRIGGER_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId());
    }*/
}
