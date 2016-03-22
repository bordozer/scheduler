package scheduler.app.services.scheduler;

import scheduler.app.models.SchedulerTask;

public class SchedulerExecutionTask implements Runnable {

    private SchedulerTask schedulerTask;

    public SchedulerExecutionTask(final SchedulerTask schedulerTask) {
        this.schedulerTask = schedulerTask;
    }

    @Override
    public void run() {

    }
}
