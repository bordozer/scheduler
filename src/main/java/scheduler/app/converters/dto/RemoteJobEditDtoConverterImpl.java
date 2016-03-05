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
		model.setPostJson(dto.getPostJson());
		return model;
	}

	@Override
	protected Function<RemoteJob, RemoteJobEditDto> taskMapper() {
		return model -> {
			RemoteJobEditDto ret = new RemoteJobEditDto();
			ret.setId(model.getId());
			ret.setUrl(model.getUrl());
			ret.setRequestMethod(model.getRequestMethod());
			ret.setPostJson(model.getPostJson());
			return ret;
		};
	}
}
