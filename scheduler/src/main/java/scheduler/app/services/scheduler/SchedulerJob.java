package scheduler.app.services.scheduler;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import scheduler.app.models.SchedulerTask;

public class SchedulerJob extends QuartzJobBean {

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        SchedulerTask schedulerTask = (SchedulerTask) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK);

        System.out.println(String.format("JOB: #%d", schedulerTask.getId()));
    }
}
