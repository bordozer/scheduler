package scheduler.core.services.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
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
    public void start() throws SchedulerException {
        if (isRunning()) {
            return;
        }
        unscheduleTasks();
        scheduleTasks();
        schedulerFactoryBean.start();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        schedulerFactoryBean.stop();
    }

    @Override
    public void scheduleTask(final Long scheduleTaskId) {

    }

    @Override
    public void unScheduleTask(final Long scheduleTaskId) {

    }

    private void scheduleTasks() {
        List<Trigger> triggers = schedulerJobService.buildSchedulerJobTriggers();
        Trigger[] cronTriggerFactoryBeen = triggers.toArray(new Trigger[triggers.size()]);
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBeen);
    }

    private void unscheduleTasks() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.getTriggerKeys(GroupMatcher.anyGroup())
                .stream()
                .forEach(this::unscheduleTrigger);
    }

    private void unscheduleTrigger(final TriggerKey triggerKey) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            LOGGER.warn(String.format("Cannot unschedule trigger: '%s'", triggerKey.getName()), e);
        }
    }

    private boolean isRunning() {
        return schedulerFactoryBean.isRunning();
    }
}
