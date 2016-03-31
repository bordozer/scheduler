package scheduler.core.services.scheduler;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import scheduler.core.services.jobs.JobExecutionService;

public class SchedulerJob extends QuartzJobBean {

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        Long remoteJobId = (Long) dataMap.get(SchedulerJobServiceImpl.SCHEDULER_TASK_REMOTE_JOB_ID);
        JobExecutionService jobExecutionService = (JobExecutionService) dataMap.get(SchedulerJobServiceImpl.JOB_EXECUTION_SERVICE);

        jobExecutionService.execute(remoteJobId);
    }
}
