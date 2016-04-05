package schemway.scheduler.services;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;
import schemway.core.models.SchedulerTask;
import schemway.core.models.User;
import schemway.core.services.tasks.SchedulerTaskService;
import schemway.scheduler.jobs.JobExecutionService;
import schemway.scheduler.models.SchedulerJobTrigger;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerJobServiceImpl implements SchedulerJobService {

    private static final Logger LOGGER = Logger.getLogger(SchedulerJobServiceImpl.class);

    public static final String SCHEDULER_TASK_REMOTE_JOB_ID = "SCHEDULER_TASK_REMOTE_JOB_ID";
    public static final String JOB_EXECUTION_SERVICE = "JOB_EXECUTION_SERVICE";

    private static final String QUARTZ_JOB_PATTERN = "QUARTZ_JOB_%d_%d";
    private static final String QUARTZ_JOB_GROUP_PATTERN = "QUARTZ_JOB_GROUP_%d";
    private static final String QUARTZ_TRIGGER_PATTERN = "QUARTZ_TRIGGER_%d_%d";

    private static final String CRON = "0/5 * * * * ?"; // TODO: temporary, will be read from Scheduler Task parameters

    @Inject
    private SchedulerTaskService schedulerTaskService;

    @Inject
    private JobExecutionService jobExecutionService;

    @Override
    public List<SchedulerJobTrigger> buildSchedulerJobTriggers() {
        return schedulerTaskService.loadAll()
                .stream()
                .map(this::buildSchedulerJobTrigger)
                .collect(Collectors.toList());
    }

    @Override
    public SchedulerJobTrigger buildSchedulerJobTrigger(final Long schedulerTaskId) {
        return buildSchedulerJobTrigger(schedulerTaskService.load(schedulerTaskId));
    }

    private SchedulerJobTrigger buildSchedulerJobTrigger(final SchedulerTask schedulerTask) {
        User user = schedulerTask.getUser();

        String jobName = getJobName(schedulerTask);
        String jobGroup = getJobGroup(user.getId());
        String triggerName = getTriggerName(user.getId(), schedulerTask.getId());
        JobKey jobKey = new JobKey(jobName, jobGroup);

        JobDataMap dataMap = new JobDataMap();
        dataMap.put(SCHEDULER_TASK_REMOTE_JOB_ID, schedulerTask.getRemoteJob().getId());
        dataMap.put(JOB_EXECUTION_SERVICE, jobExecutionService);

        JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                .withIdentity(jobKey)
                .setJobData(dataMap)
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName)
                .withDescription(schedulerTask.getTaskDescription())
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON))
                .build();

        return SchedulerJobTrigger.builder().
                schedulerTaskId(schedulerTask.getId())
                .jobDetail(job)
                .trigger(trigger)
                .build();
    }

    private String getJobName(final SchedulerTask schedulerTask) {
        return String.format(QUARTZ_JOB_PATTERN, schedulerTask.getUser().getId(), schedulerTask.getId());
    }

    private String getJobGroup(final Long userId) {
        return String.format(QUARTZ_JOB_GROUP_PATTERN, userId);
    }

    private String getTriggerName(final Long userId, final Long schedulerTaskId) {
        return String.format(QUARTZ_TRIGGER_PATTERN, userId, schedulerTaskId);
    }
}
