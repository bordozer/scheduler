package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDto;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

import java.util.function.Function;

@Service
public class SchedulerTaskEditDtoConverterImpl extends AbstractToDtoConverter<SchedulerTask, SchedulerTaskEditDto> implements SchedulerTaskEditDtoConverter {

	@Override
	public SchedulerTask toModel(final User user, final SchedulerTaskEditDto editDto) {
		SchedulerTask ret = new SchedulerTask();
		ret.setId(editDto.getTaskId());
		ret.setUser(user);
		ret.setTaskName(editDto.getTaskName());
		ret.setTaskDescription(editDto.getTaskDescription());
		return ret;
	}

	@Override
	public Function<SchedulerTask, SchedulerTaskEditDto> taskMapper() {
		return model -> {
			SchedulerTaskEditDto ret = new SchedulerTaskEditDto();
			ret.setTaskId(model.getId());
			ret.setTaskName(model.getTaskName());
			ret.setTaskDescription(model.getTaskDescription());
			return ret;
		};
	}
}
