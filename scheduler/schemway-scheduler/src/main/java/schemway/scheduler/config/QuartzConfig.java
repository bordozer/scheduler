package schemway.scheduler.config;

import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import schemway.core.services.SystemVarsService;
import schemway.scheduler.services.SchedulerService;

import javax.inject.Inject;

@Configuration
@EnableAsync
@EnableScheduling
public class QuartzConfig {

    public static final String SCHEDULER_MICRO_SERVICE = "SCHEDULER_MICRO_SERVICE";

    @Inject
    private SystemVarsService systemVarsService;

    @Inject
    private SchedulerService schedulerService;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setSchedulerName(SCHEDULER_MICRO_SERVICE);
        schedulerFactory.setStartupDelay(systemVarsService.schedulerStartupDelay());
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.start();
        return schedulerFactory;
    }

    @Bean(initMethod = "init")
    public SchedulerRunner schedulerRunner() {
        return new SchedulerRunner();
    }

    public class SchedulerRunner {

        public void init() throws SchedulerException {
            if (systemVarsService.schedulerAutorun()) {
                schedulerService.startScheduler();
            }
        }
    }
}
