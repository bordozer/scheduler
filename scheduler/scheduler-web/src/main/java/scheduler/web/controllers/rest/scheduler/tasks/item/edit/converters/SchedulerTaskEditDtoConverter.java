package scheduler.web.controllers.rest.scheduler.tasks.item.edit.converters;

import org.springframework.stereotype.Service;
import scheduler.core.models.SchedulerTask;
import scheduler.core.models.User;
import scheduler.web.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.web.converters.dto.AbstractGenericDtoConverter;

import javax.inject.Inject;
import java.util.function.Function;

@Service
public class SchedulerTaskEditDtoConverter extends AbstractGenericDtoConverter<SchedulerTask, SchedulerTaskEditDto> {

	@Inject
	private RemoteJobEditDtoConverter remoteJobEditDtoConverter;

	@Override
	protected SchedulerTask doConvertToModel(final User user, final SchedulerTaskEditDto editDto) {
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