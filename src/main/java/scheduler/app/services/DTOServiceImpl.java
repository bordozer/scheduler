package scheduler.app.services;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.list.TaskEntryDTO;
import scheduler.app.entries.SchedulerTaskEntry;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DTOServiceImpl implements DTOService {

    @Override
    public TaskEntryDTO transformTask(final SchedulerTaskEntry task) {
        return taskMapper().apply(task);
    }

    @Override
    public List<TaskEntryDTO> transformTasks(final List<SchedulerTaskEntry> taskEntries) {
        return taskEntries.stream().map(taskMapper()).collect(Collectors.toList());
    }

    private Function<SchedulerTaskEntry, TaskEntryDTO> taskMapper() {

        return new Function<SchedulerTaskEntry, TaskEntryDTO>() {

            @Override
            public TaskEntryDTO apply(final SchedulerTaskEntry task) {

                final TaskEntryDTO dto = new TaskEntryDTO();

                dto.setTaskId(task.getId());
                dto.setTaskName(task.getTaskName());
                dto.setDescription(task.getDescription());
                dto.setTaskParametersJSON(task.getTaskParametersJSON());
                dto.setRemoteURL(task.getRemoteURL());
                dto.setRemoteParametersJSON(task.getRemoteParametersJSON());

                return dto;
            }
        };
    }
}
