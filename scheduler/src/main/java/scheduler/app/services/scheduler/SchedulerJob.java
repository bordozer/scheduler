package scheduler.app.services.scheduler;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import scheduler.app.models.User;
import scheduler.app.services.jobs.JobExecutionService;

public class SchedulerJob extends QuartzJobBean {

    private static final Logger LOGGER = Logger.getLogger(SchedulerJob.class);

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        Long remoteJobId = (Long) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK_REMOTE_JOB_ID);
        JobExecutionService jobExecutionService = (JobExecutionService) dataMap.get(SchedulerTaskInitializationServiceImpl.JOB_EXECUTION_SERVICE);
        User user = (User) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK_USER);

        jobExecutionService.execute(remoteJobId);
    }
}
