package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
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
	public SchedulerTask toModel(final User user, final SchedulerTaskDTO dto) {
		SchedulerTask ret = new SchedulerTask();
		ret.setId(dto.getTaskId());
		ret.setUser(user);
		ret.setTaskName(dto.getTaskName());
		ret.setTaskDescription(dto.getTaskDescription());
		return ret;
	}

	private Function<SchedulerTask, SchedulerTaskDTO> taskMapper() {
		return model -> {
			final SchedulerTaskDTO dto = new SchedulerTaskDTO();
			dto.setTaskId(model.getId());
			dto.setTaskName(model.getTaskName());
			dto.setTaskDescription(model.getTaskDescription());
//                dto.setDescription(task.getTaskDescription());
//                dto.setTaskParametersJSON(task.getTaskParametersJSON());
//                dto.setRemoteURL(task.getRemoteURL());
//                dto.setRemoteParametersJSON(task.getRemoteParametersJSON());
			return dto;
		};
	}
}
