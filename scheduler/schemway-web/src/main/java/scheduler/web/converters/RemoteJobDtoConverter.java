package scheduler.web.converters;

import org.springframework.stereotype.Service;
import scheduler.core.models.RemoteJob;
import scheduler.core.models.User;
import scheduler.web.dto.RemoteJobDto;

import java.util.function.Function;

@Service
public class RemoteJobDtoConverter extends AbstractGenericDtoConverter<RemoteJob, RemoteJobDto> {

	@Override
	protected RemoteJob doConvertToModel(final User user, final RemoteJobDto dto) {
		final RemoteJob model = new RemoteJob();
		model.setId(dto.getRemoteJobId());
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
			dto.setRemoteJobId(model.getId());
			dto.setRequestUrl(model.getRequestUrl());
			dto.setRequestMethod(model.getRequestMethod());
			dto.setAuthString(model.getAuthString());
			dto.setPostJson(model.getPostJson());
			return dto;
		};
	}
}
