package scheduler.app.converters.dto;

import scheduler.app.controllers.rest.tasks.list.SchedulerTaskDTO;
import scheduler.app.models.SchedulerTask;

import java.util.List;

public interface DTOService {

    SchedulerTaskDTO transformTask(SchedulerTask task);

    List<SchedulerTaskDTO> transformTasks(final List<SchedulerTask> taskEntries);
}
