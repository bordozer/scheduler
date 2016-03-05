package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDto;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

import javax.inject.Inject;
import java.util.function.Function;

@Service
public class SchedulerTaskEditDtoConverterImpl extends AbstractToDtoConverter<SchedulerTask, SchedulerTaskEditDto> implements SchedulerTaskEditDtoConverter {

	@Inject
	private RemoteJobEditDtoConverter remoteJobEditDtoConverter;

	@Override
	public SchedulerTask toModel(final User user, final SchedulerTaskEditDto editDto) {
		SchedulerTask model = new SchedulerTask();
		model.setId(editDto.getTaskId());
		model.setUser(user);
		model.setTaskType(editDto.getTaskType());
		model.setTaskName(editDto.getTaskName());
		model.setTaskDescription(editDto.getTaskDescription());
		model.setTaskParametersJSON(editDto.getTaskParametersJSON());
		model.setRemoteJob(remoteJobEditDtoConverter.toModel(user, editDto.getRemoteJob()));
		return model;
	}

	@Override
	public Function<SchedulerTask, SchedulerTaskEditDto> taskMapper() {
		return model -> {
			SchedulerTaskEditDto ret = new SchedulerTaskEditDto();
			ret.setTaskId(model.getId());
			ret.setTaskType(model.getTaskType());
			ret.setTaskName(model.getTaskName());
			ret.setTaskDescription(model.getTaskDescription());
			ret.setTaskParametersJSON(model.getTaskParametersJSON());
			ret.setRemoteJob(remoteJobEditDtoConverter.toDto(model.getRemoteJob()));
			return ret;
		};
	}
}
