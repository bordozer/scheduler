package scheduler.config.root;

import org.quartz.SchedulerException;
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
import java.util.Properties;

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
//        schedulerFactory.setQuartzProperties(getQuartzProperties());
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        return schedulerFactory;
    }

    /*private Properties getQuartzProperties() {
        Properties properties = new Properties();
        properties.setProperty("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        return properties;
    }*/
}
