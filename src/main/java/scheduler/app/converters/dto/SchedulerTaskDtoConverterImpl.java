package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDTO;
import scheduler.app.dto.SchedulerTaskDTO;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskDtoConverterImpl implements SchedulerTaskDtoConverter {

    @Override
    public SchedulerTaskDTO toDto(final SchedulerTask task) {
        return taskMapper().apply(task);
    }

    @Override
    public List<SchedulerTaskDTO> toDtos(final List<SchedulerTask> taskEntries) {
        return taskEntries.stream().map(taskMapper()).collect(Collectors.toList());
    }

    @Override
    public SchedulerTaskEditDTO toEditDto(final SchedulerTask schedulerTask) {
        SchedulerTaskEditDTO ret = new SchedulerTaskEditDTO();
        ret.setTaskId(schedulerTask.getId());
        ret.setTaskName(schedulerTask.getTaskName());
        ret.setTaskDescription(schedulerTask.getTaskDescription());
        return ret;
    }

    @Override
    public SchedulerTask toModel(final User user, final SchedulerTaskDTO dto) {
        SchedulerTask ret = new SchedulerTask();
        ret.setId(dto.getTaskId());
        ret.setUser(user);
        ret.setTaskName(dto.getTaskName());
        ret.setTaskDescription(dto.getTaskDescription());
        return ret;
    }

    @Override
    public SchedulerTask toModel(final User user, final SchedulerTaskEditDTO editDto) {
        SchedulerTask ret = new SchedulerTask();
        ret.setId(editDto.getTaskId());
        ret.setUser(user);
        ret.setTaskName(editDto.getTaskName());
        ret.setTaskDescription(editDto.getTaskDescription());
        return ret;
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
