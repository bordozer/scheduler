package scheduler.config.root;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import scheduler.app.services.scheduler.SchedulerTaskInitializationService;

import javax.inject.Inject;
import java.util.List;

@Configuration
@EnableAsync
@EnableScheduling
public class QuartzConfig {

    @Inject
    private SchedulerTaskInitializationService schedulerTaskInitializationService;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws SchedulerException {
        List<Trigger> triggers = schedulerTaskInitializationService.initSchedulerTasks();
        Trigger[] cronTriggerFactoryBeen = triggers.toArray(new Trigger[triggers.size()]);

        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setSchedulerName("SCHEDULER_MICRO_SERVICE");
        scheduler.setTriggers(cronTriggerFactoryBeen);
        scheduler.setStartupDelay(0); // TODO: startup delay, set to 120 (seconds)

        return scheduler;
    }
}
