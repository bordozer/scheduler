package scheduler.scheduler.services;

import org.apache.log4j.Logger;
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
        schedulerFactoryBean.setTriggers(schedulerJobService.buildSchedulerJobTrigger(scheduleTaskId));
    }

    @Override
    public void unScheduleTask(final Long scheduleTaskId) {
//        getScheduler().getJobDetail()
    }

    @Override
    public void scheduleAllTasks() {
        List<Trigger> triggers = schedulerJobService.buildSchedulerJobTriggers();
        Trigger[] cronTriggerFactoryBeen = triggers.toArray(new Trigger[triggers.size()]);
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBeen);
        /*try {
            getScheduler().scheduleJob(cronTriggerFactoryBeen[0]);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void unscheduleAllTasks() throws SchedulerException {
        getScheduler().clear();
        /*Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.getTriggerKeys(GroupMatcher.anyGroup())
                .stream()
                .forEach(this::unscheduleTrigger);*/
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
