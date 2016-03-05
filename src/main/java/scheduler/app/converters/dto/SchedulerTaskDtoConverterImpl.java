package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.dto.SchedulerTaskDto;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

import javax.inject.Inject;
import java.util.function.Function;

@Service
public class SchedulerTaskDtoConverterImpl extends AbstractToDtoConverter<SchedulerTask, SchedulerTaskDto>  implements SchedulerTaskDtoConverter {

	@Inject
	private RemoteJobDtoConverter remoteJobDtoConverter;

	@Override
	public SchedulerTask toModel(final User user, final SchedulerTaskDto dto) {
		SchedulerTask model = new SchedulerTask();
		model.setId(dto.getTaskId());
		model.setUser(user);
		model.setTaskType(dto.getTaskType());
		model.setTaskName(dto.getTaskName());
		model.setTaskDescription(dto.getTaskDescription());
		model.setTaskParametersJSON(dto.getTaskParametersJSON());
		model.setRemoteJob(remoteJobDtoConverter.toModel(user, dto.getRemoteJob()));
		return model;
	}

	protected Function<SchedulerTask, SchedulerTaskDto> taskMapper() {
		return model -> {
			final SchedulerTaskDto dto = new SchedulerTaskDto();
			dto.setTaskId(model.getId());
			dto.setTaskType(model.getTaskType());
			dto.setTaskName(model.getTaskName());
			dto.setTaskDescription(model.getTaskDescription());
			dto.setTaskParametersJSON(model.getTaskParametersJSON());
			dto.setRemoteJob(remoteJobDtoConverter.toDto(model.getRemoteJob()));
			return dto;
		};
	}
}
