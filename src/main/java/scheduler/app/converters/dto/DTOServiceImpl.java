package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.list.SchedulerTaskDTO;
import scheduler.app.models.SchedulerTask;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DTOServiceImpl implements DTOService {

    @Override
    public SchedulerTaskDTO transformTask(final SchedulerTask task) {
        return taskMapper().apply(task);
    }

    @Override
    public List<SchedulerTaskDTO> transformTasks(final List<SchedulerTask> taskEntries) {
        return taskEntries.stream().map(taskMapper()).collect(Collectors.toList());
    }

    private Function<SchedulerTask, SchedulerTaskDTO> taskMapper() {

        return task -> {
            final SchedulerTaskDTO dto = new SchedulerTaskDTO();
            dto.setTaskId(task.getId());
            dto.setTaskName(task.getTaskName());
            dto.setTaskDescription(task.getTaskDescription());
//                dto.setDescription(task.getTaskDescription());
//                dto.setTaskParametersJSON(task.getTaskParametersJSON());
//                dto.setRemoteURL(task.getRemoteURL());
//                dto.setRemoteParametersJSON(task.getRemoteParametersJSON());
            return dto;
        };
    }
}
