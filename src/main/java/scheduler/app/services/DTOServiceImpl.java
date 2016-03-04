package scheduler.app.services;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.list.TaskEntryDTO;
import scheduler.app.entries.TaskEntry;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DTOServiceImpl implements DTOService {

    @Override
    public TaskEntryDTO transformTask(final TaskEntry task) {
        return taskMapper().apply(task);
    }

    @Override
    public List<TaskEntryDTO> transformTasks(final List<TaskEntry> taskEntries) {
        return taskEntries.stream().map(taskMapper()).collect(Collectors.toList());
    }

    private Function<TaskEntry, TaskEntryDTO> taskMapper() {

        return new Function<TaskEntry, TaskEntryDTO>() {

            @Override
            public TaskEntryDTO apply(final TaskEntry task) {

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
