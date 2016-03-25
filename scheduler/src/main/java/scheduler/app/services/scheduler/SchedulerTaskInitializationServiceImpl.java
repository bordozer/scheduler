package scheduler.app.services.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Service;
import scheduler.app.models.SchedulerTask;
import scheduler.app.services.jobs.JobExecutionService;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskInitializationServiceImpl implements SchedulerTaskInitializationService {

    public static final String SCHEDULER_TASK_REMOTE_JOB_ID = "SCHEDULER_TASK_REMOTE_JOB_ID";
    public static final String JOB_EXECUTION_SERVICE = "JOB_EXECUTION_SERVICE";
    public static final String SCHEDULER_TASK_USER = "SCHEDULER_TASK_USER";

    private static final String CRON = "0/15 * * * * ?";

    @Inject
    private SchedulerTaskService schedulerTaskService;

    @Inject
    private JobExecutionService jobExecutionService;

    @Override
    public List<Trigger> buildSchedulerJobTriggers() throws SchedulerException {

        List<SchedulerTask> schedulerTasks = schedulerTaskService.loadAll();

        return schedulerTasks.stream()
                .map(schedulerTask -> {
                    String jobName = getJobName(schedulerTask);
                    String jobGroup = getJobGroup(schedulerTask);
                    String triggerName = getTriggerName(schedulerTask);
                    JobKey jobKey = new JobKey(jobName, jobGroup);

                    JobDataMap dataMap = new JobDataMap();
                    dataMap.put(SCHEDULER_TASK_REMOTE_JOB_ID, schedulerTask.getRemoteJob().getId());
                    dataMap.put(SCHEDULER_TASK_USER, schedulerTask.getUser());
                    dataMap.put(JOB_EXECUTION_SERVICE, jobExecutionService);

                    JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                            .withIdentity(jobKey)
                            .setJobData(dataMap)
                            .build();
                    try {
                        return cronTriggerFactoryBean(job, triggerName, jobGroup, CRON).getObject();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }

                }).collect(Collectors.toList());
    }

    private CronTriggerFactoryBean cronTriggerFactoryBean(final JobDetail job, final String triggerName,
                                                          final String group, final String crone) throws ParseException {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(job);
        stFactory.setName(triggerName);
        stFactory.setGroup(group);
        stFactory.setCronExpression(crone);
        stFactory.afterPropertiesSet();

        return stFactory;
    }

    private String getJobName(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_JOB_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId());
    }

    private String getJobGroup(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_JOB_GROUP_%d", schedulerTask.getUser().getId());
    }

    private String getTriggerName(final SchedulerTask schedulerTask) {
        return String.format("QUARTZ_TRIGGER_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId());
    }
}
