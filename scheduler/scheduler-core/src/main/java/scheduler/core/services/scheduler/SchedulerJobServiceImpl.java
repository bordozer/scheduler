package scheduler.core.services.scheduler;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Service;
import scheduler.core.models.SchedulerTask;
import scheduler.core.services.jobs.JobExecutionService;
import scheduler.core.services.tasks.SchedulerTaskService;

import javax.inject.Inject;
import java.text.ParseException;
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
    public List<Trigger> buildSchedulerJobTriggers() {
        return schedulerTaskService.loadAll()
                .stream()
                .map(this::buildSchedulerJobTrigger)
                .collect(Collectors.toList());
    }

    @Override
    public CronTrigger buildSchedulerJobTrigger(final SchedulerTask schedulerTask) {
        String jobName = getJobName(schedulerTask);
        String jobGroup = getJobGroup(schedulerTask);
        String triggerName = getTriggerName(schedulerTask);
        JobKey jobKey = new JobKey(jobName, jobGroup);

        JobDataMap dataMap = new JobDataMap();
        dataMap.put(SCHEDULER_TASK_REMOTE_JOB_ID, schedulerTask.getRemoteJob().getId());
        dataMap.put(JOB_EXECUTION_SERVICE, jobExecutionService);

        JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                .withIdentity(jobKey)
                .setJobData(dataMap)
                .build();
        try {
            return cronTriggerFactoryBean(job, triggerName, jobGroup, CRON).getObject();
        } catch (ParseException e) {
            LOGGER.error(String.format("Error scheduling task: %s", schedulerTask), e);
            return null;
        }
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
        return String.format(QUARTZ_JOB_PATTERN, schedulerTask.getUser().getId(), schedulerTask.getId());
    }

    private String getJobGroup(final SchedulerTask schedulerTask) {
        return String.format(QUARTZ_JOB_GROUP_PATTERN, schedulerTask.getUser().getId());
    }

    private String getTriggerName(final SchedulerTask schedulerTask) {
        return String.format(QUARTZ_TRIGGER_PATTERN, schedulerTask.getUser().getId(), schedulerTask.getId());
    }
}
