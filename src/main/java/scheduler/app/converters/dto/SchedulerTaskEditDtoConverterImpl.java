package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDTO;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskEditDtoConverterImpl extends AbstractEditDtoConverter implements SchedulerTaskEditDtoConverter {

	/*@Override
	public SchedulerTaskEditDTO toDto(final SchedulerTask schedulerTask) {
		return taskMapper().apply(schedulerTask);
	}

	@Override
	public List<SchedulerTaskEditDTO> toDtos(final List<SchedulerTask> schedulerTasks) {
		return schedulerTasks.stream().map(taskMapper()).collect(Collectors.toList());
	}*/

	@Override
	public SchedulerTask toModel(final User user, final SchedulerTaskEditDTO editDto) {
		SchedulerTask ret = new SchedulerTask();
		ret.setId(editDto.getTaskId());
		ret.setUser(user);
		ret.setTaskName(editDto.getTaskName());
		ret.setTaskDescription(editDto.getTaskDescription());
		return ret;
	}

	@Override
	public Function<SchedulerTask, SchedulerTaskEditDTO> taskMapper() {
		return model -> {
			SchedulerTaskEditDTO ret = new SchedulerTaskEditDTO();
			ret.setTaskId(model.getId());
			ret.setTaskName(model.getTaskName());
			ret.setTaskDescription(model.getTaskDescription());
			return ret;
		};
	}
}
