package scheduler.core.services.scheduler;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SchedulerJob extends QuartzJobBean {

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        Long remoteJobId = (Long) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK_REMOTE_JOB_ID);
        scheduler.core.services.jobs.JobExecutionService jobExecutionService = (scheduler.core.services.jobs.JobExecutionService) dataMap.get(SchedulerTaskInitializationServiceImpl.JOB_EXECUTION_SERVICE);

        jobExecutionService.execute(remoteJobId);
    }
}
