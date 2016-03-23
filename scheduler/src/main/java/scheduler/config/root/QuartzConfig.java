package scheduler.config.root;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
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
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        List<Trigger> triggers = schedulerTaskInitializationService.buildSchedulerJobTriggers();
        Trigger[] cronTriggerFactoryBeen = triggers.toArray(new Trigger[triggers.size()]);

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setSchedulerName("SCHEDULER_MICRO_SERVICE");
        schedulerFactory.setTriggers(cronTriggerFactoryBeen);
        schedulerFactory.setStartupDelay(0); // TODO: startup delay, set to 120 (seconds)
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        schedulerFactory.start();

        return schedulerFactory;
    }

}
