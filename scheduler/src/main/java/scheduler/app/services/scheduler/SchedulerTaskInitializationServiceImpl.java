package scheduler.app.services.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;
import scheduler.app.models.SchedulerTask;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskInitializationServiceImpl implements SchedulerTaskInitializationService {

    private static final String SHIPMENT_PICKUP_ALERT_INTERVAL_DEFAULT = "0 0/1 * * * ?"; // each minute

    @Inject
    private SchedulerTaskService schedulerTaskService;;

    @Override
    public void initSchedulerTasks(Scheduler scheduler) throws SchedulerException {
        List<SchedulerTask> schedulerTasks = schedulerTaskService.loadAll();

        List<CronTriggerFactoryBean> triggerFactoryBeans = schedulerTasks.stream()
                .map(this::cronTriggerFactoryBean)
                .collect(Collectors.toList());

        triggerFactoryBeans.stream().forEach(trigger -> {
                    try {
                        scheduler.scheduleJob(trigger.getObject());
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
        );

        scheduler.start();
    }

    public CronTriggerFactoryBean cronTriggerFactoryBean(final SchedulerTask schedulerTask) {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(methodInvokingJobDetailFactoryBean(schedulerTask).getObject());
        stFactory.setName(String.format("CRON_TRIGGER_%d_%d", schedulerTask.getUser().getId(), schedulerTask.getId()));
        stFactory.setGroup(String.format("CRON_GROUP_%d", schedulerTask.getUser().getId()));
        stFactory.setCronExpression(SHIPMENT_PICKUP_ALERT_INTERVAL_DEFAULT); // TODO: depends from task type
        return stFactory;
    }

    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(final SchedulerTask schedulerTask) {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("schedulerTaskExecutionServiceImpl");
        obj.setTargetMethod("execute");
        obj.setArguments(new Object[]{schedulerTask});
        return obj;
    }
}
