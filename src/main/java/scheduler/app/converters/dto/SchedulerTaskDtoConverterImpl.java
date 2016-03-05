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
		SchedulerTask ret = new SchedulerTask();
		ret.setId(dto.getTaskId());
		ret.setUser(user);
		ret.setTaskType(dto.getTaskType());
		ret.setTaskName(dto.getTaskName());
		ret.setTaskDescription(dto.getTaskDescription());
		ret.setTaskParametersJSON(dto.getTaskParametersJSON());
		ret.setRemoteJob(remoteJobDtoConverter.toModel(user, dto.getRemoteJob()));
		return ret;
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
