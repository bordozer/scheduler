package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.tasks.item.edit.RemoteJobEditDto;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;

import java.util.function.Function;

@Service
public class RemoteJobEditDtoConverterImpl extends AbstractToDtoConverter<RemoteJob, RemoteJobEditDto> implements RemoteJobEditDtoConverter {

	@Override
	public RemoteJob toModel(final User user, final RemoteJobEditDto dto) {
		RemoteJob model = new RemoteJob();
		model.setId(dto.getId());
		model.setUrl(dto.getUrl());
		model.setRequestMethod(dto.getRequestMethod());
		model.setAuthString(dto.getAuthString());
		model.setPostJson(dto.getPostJson());
		return model;
	}

	@Override
	protected Function<RemoteJob, RemoteJobEditDto> taskMapper() {
		return model -> {
			RemoteJobEditDto dto = new RemoteJobEditDto();
			dto.setId(model.getId());
			dto.setUrl(model.getUrl());
			dto.setRequestMethod(model.getRequestMethod());
			dto.setAuthString(model.getAuthString());
			dto.setPostJson(model.getPostJson());
			return dto;
		};
	}
}