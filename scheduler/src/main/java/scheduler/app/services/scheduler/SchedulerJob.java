package scheduler.app.services.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import scheduler.app.models.SchedulerTask;

public class SchedulerJob extends QuartzJobBean {

    private SchedulerTask schedulerTask;

    public SchedulerJob(final SchedulerTask schedulerTask) {
        this.schedulerTask = schedulerTask;
    }

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        System.out.println(String.format("JOB: #%d", schedulerTask.getId()));
    }

}
