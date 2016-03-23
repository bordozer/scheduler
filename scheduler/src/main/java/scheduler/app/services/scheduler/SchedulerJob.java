package scheduler.app.services.scheduler;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import scheduler.app.models.RemoteJob;

public class SchedulerJob extends QuartzJobBean {

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        RemoteJob remoteJob = (RemoteJob) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK_REMOTE_JOB);

        System.out.println(String.format("REMOTE JOB: #%d (%s)", remoteJob.getId(), remoteJob.getRequestUrl()));
    }
}
