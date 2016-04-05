package schemway.scheduler.services;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import schemway.scheduler.models.ScheduledTask;

import javax.inject.Inject;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final Logger LOGGER = Logger.getLogger(SchedulerServiceImpl.class);

    @Inject
    private SchedulerFactoryBean schedulerFactoryBean;

    @Inject
    private SchedulerJobService schedulerJobService;

    @Override
    public void startScheduler() throws SchedulerException {
        if (isRunning()) {
            return;
        }
        unscheduleAllTasks();
        scheduleAllTasks();
        schedulerFactoryBean.start();
    }

    @Override
    public void stopScheduler() {
        if (!isRunning()) {
            return;
        }
        schedulerFactoryBean.stop();
    }

    @Override
    public void scheduleTask(final Long scheduleTaskId) {
        doScheduleTask(schedulerJobService.buildScheduledTask(scheduleTaskId));
    }

    @Override
    public void unScheduleTask(final Long scheduleTaskId) {
        throw new NotImplementedException();
    }

    @Override
    public void scheduleAllTasks() {
        List<ScheduledTask> scheduledTasks = schedulerJobService.buildScheduledTasks();
        if (scheduledTasks == null || scheduledTasks.size() == 0) {
            return;
        }
        scheduledTasks.stream().forEach(this::doScheduleTask);
        LOGGER.debug(String.format("%d scheduler tasks have been scheduled successfully", scheduledTasks.size()));
    }

    @Override
    public void unscheduleAllTasks() throws SchedulerException {
        getScheduler().clear();
        LOGGER.debug("All scheduler tasks have been cleaned up successfully");
    }

    @Override
    public boolean isRunning() {
        return schedulerFactoryBean.isRunning();
    }

    private void doScheduleTask(final ScheduledTask scheduledTask) {
        Assert.notNull(scheduledTask, "Scheduled Task must not be null");

        Scheduler scheduler = getScheduler();
        try {
            scheduler.scheduleJob(scheduledTask.getJobDetail(), scheduledTask.getTrigger());
            LOGGER.debug(String.format("Scheduler task '%s' have been scheduled successfully", scheduledTask.getSchedulerTaskId()));
        } catch (SchedulerException e) {
            LOGGER.error(String.format("Scheduler task '%s' scheduling failed", scheduledTask.getSchedulerTaskId()), e);
        }
    }

    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }
}
