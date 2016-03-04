package scheduler.app.services;

import scheduler.app.controllers.rest.tasks.list.TaskEntryDTO;
import scheduler.app.entries.SchedulerTaskEntry;

import java.util.List;

public interface DTOService {

    TaskEntryDTO transformTask(SchedulerTaskEntry task);

    List<TaskEntryDTO> transformTasks(final List<SchedulerTaskEntry> taskEntries);
}
