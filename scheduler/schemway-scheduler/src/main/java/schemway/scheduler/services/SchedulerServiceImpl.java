package schemway.scheduler.services;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

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
//        schedulerFactoryBean.setTriggers(schedulerJobService.buildSchedulerJobTrigger(scheduleTaskId));
    }

    @Override
    public void unScheduleTask(final Long scheduleTaskId) {
//        getScheduler().getJobDetail()
    }

    @Override
    public void scheduleAllTasks() {
        List<Trigger> triggers = schedulerJobService.buildSchedulerJobTriggers();

        if (triggers == null || triggers.size() == 0) {
            return;
        }

        Scheduler scheduler = getScheduler();

        Trigger[] cronTriggerFactoryBeen = triggers.toArray(new Trigger[triggers.size()]);
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBeen);

        triggers.stream().forEach(trigger -> {
            try {
                JobDetail jobDetail = (JobDetail) trigger.getJobDataMap().get("jobDetail"); // TODO: bad way, but for temporary - ok
                scheduler.scheduleJob(jobDetail, trigger);
                LOGGER.debug(String.format("Scheduler task '%s' have been scheduled successfully", trigger.getKey()));
            } catch (SchedulerException e) {
                LOGGER.error(String.format("Scheduler task '%s' scheduling failed", trigger.getKey()), e);
            }
        });

//        Trigger[] cronTriggerFactoryBeen = triggers.toArray(new Trigger[triggers.size()]);
//        schedulerFactoryBean.setTriggers(cronTriggerFactoryBeen);

        LOGGER.debug(String.format("%d scheduler tasks have been scheduled successfully", triggers.size()));
    }

    @Override
    public void unscheduleAllTasks() throws SchedulerException {
        getScheduler().clear();
        /*Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.getTriggerKeys(GroupMatcher.anyGroup())
                .stream()
                .forEach(this::unscheduleTrigger);*/
        LOGGER.debug("All scheduler tasks have been cleaned up successfully");
    }

    @Override
    public boolean isRunning() {
        return schedulerFactoryBean.isRunning();
    }

    private void unscheduleTrigger(final TriggerKey triggerKey) {
        Scheduler scheduler = getScheduler();
        try {
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            LOGGER.warn(String.format("Cannot unschedule trigger: '%s'", triggerKey.getName()), e);
        }
    }

    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }
}
