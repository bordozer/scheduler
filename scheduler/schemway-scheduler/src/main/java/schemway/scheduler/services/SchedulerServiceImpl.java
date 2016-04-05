package schemway.scheduler.services;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import schemway.scheduler.models.SchedulerJobModel;

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
        List<SchedulerJobModel> schedulerJobModels = schedulerJobService.buildScheduledTasks();
        if (schedulerJobModels == null || schedulerJobModels.size() == 0) {
            return;
        }
        schedulerJobModels.stream().forEach(this::doScheduleTask);
        LOGGER.debug(String.format("%d scheduler tasks have been scheduled successfully", schedulerJobModels.size()));
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

    private void doScheduleTask(final SchedulerJobModel schedulerJobModel) {
        Assert.notNull(schedulerJobModel, "Scheduled Task must not be null");

        Scheduler scheduler = getScheduler();
        try {
            scheduler.scheduleJob(schedulerJobModel.getJobDetail(), schedulerJobModel.getTrigger());
            LOGGER.debug(String.format("Scheduler task '%s' have been scheduled successfully", schedulerJobModel.getSchedulerTaskId()));
        } catch (SchedulerException e) {
            LOGGER.error(String.format("Scheduler task '%s' scheduling failed", schedulerJobModel.getSchedulerTaskId()), e);
        }
    }

    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }
}
