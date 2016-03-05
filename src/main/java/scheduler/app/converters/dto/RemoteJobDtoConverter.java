package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.dto.RemoteJobDto;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;

import java.util.function.Function;

@Service
public class RemoteJobDtoConverter extends AbstractGenericDtoConverter<RemoteJob, RemoteJobDto> {

	@Override
	public RemoteJob toModel(final User user, final RemoteJobDto dto) {
		final RemoteJob model = new RemoteJob();
		model.setId(dto.getId());
		model.setRequestUrl(dto.getRequestUrl());
		model.setRequestMethod(dto.getRequestMethod());
		model.setAuthString(dto.getAuthString());
		model.setPostJson(dto.getPostJson());
		return model;
	}

	@Override
	protected Function<RemoteJob, RemoteJobDto> taskMapper() {
		return model -> {
			final RemoteJobDto dto = new RemoteJobDto();
			dto.setId(model.getId());
			dto.setRequestUrl(model.getRequestUrl());
			dto.setRequestMethod(model.getRequestMethod());
			dto.setAuthString(model.getAuthString());
			dto.setPostJson(model.getPostJson());
			return dto;
		};
	}
}
