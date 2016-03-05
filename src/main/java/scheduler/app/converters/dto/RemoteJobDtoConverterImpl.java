package scheduler.app.converters.dto;

import scheduler.app.dto.RemoteJobDto;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;

import java.util.function.Function;

public class RemoteJobDtoConverterImpl extends AbstractToDtoConverter<RemoteJob, RemoteJobDto> implements RemoteJobDtoConverter {

	@Override
	public RemoteJob toModel(final User user, final RemoteJobDto dto) {
		final RemoteJob model = new RemoteJob();
		model.setId(dto.getId());
		model.setUrl(dto.getUrl());
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
			dto.setUrl(model.getUrl());
			dto.setRequestMethod(model.getRequestMethod());
			dto.setAuthString(model.getAuthString());
			dto.setPostJson(model.getPostJson());
			return dto;
		};
	}
}
