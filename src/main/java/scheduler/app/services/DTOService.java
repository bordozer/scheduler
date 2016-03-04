package scheduler.app.services;

import scheduler.app.controllers.rest.tasks.list.TaskEntryDTO;
import scheduler.app.entries.TaskEntry;

import java.util.List;

public interface DTOService {

    TaskEntryDTO transformTask(TaskEntry task);

    List<TaskEntryDTO> transformTasks(final List<TaskEntry> taskEntries);
}
