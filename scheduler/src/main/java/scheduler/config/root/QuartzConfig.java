package scheduler.config.root;

import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import scheduler.app.services.scheduler.SchedulerTaskInitializationService;

import javax.inject.Inject;

@Configuration
@EnableAsync
@EnableScheduling
public class QuartzConfig {

    @Inject
    private SchedulerTaskInitializationService schedulerTaskInitializationService;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws SchedulerException {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setSchedulerName("SCHEDULER_MICRO_SERVICE");
        scheduler.setStartupDelay(0); // TODO: startup delay, set to 120 (seconds)

        schedulerTaskInitializationService.initSchedulerTasks(scheduler.getObject());

        return scheduler;
    }
}
