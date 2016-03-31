package scheduler.core.services.scheduler;

import org.springframework.stereotype.Service;
import scheduler.core.models.SchedulerTask;

@Service
public class SchedulerTaskExecutionServiceImpl implements SchedulerTaskExecutionService {

    @Override
    public void execute(final SchedulerTask schedulerTask) {
        int i = 0;
    }
}
