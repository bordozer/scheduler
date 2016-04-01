package schemway.web.controllers.rest.scheduler.tasks.item.edit.converters;

import org.springframework.stereotype.Service;
import schemway.core.models.RemoteJob;
import schemway.core.models.User;
import schemway.web.controllers.rest.scheduler.tasks.item.edit.dto.RemoteJobEditDto;
import schemway.web.converters.AbstractGenericDtoConverter;

import java.util.function.Function;

@Service
public class RemoteJobEditDtoConverter extends AbstractGenericDtoConverter<RemoteJob, RemoteJobEditDto> {

	@Override
	protected RemoteJob doConvertToModel(final User user, final RemoteJobEditDto dto) {
		RemoteJob model = new RemoteJob();
		model.setId(dto.getRemoteJobId());
		model.setRequestUrl(dto.getRequestUrl());
		model.setRequestMethod(dto.getRequestMethod());
		model.setAuthString(dto.getAuthString());
		model.setPostJson(dto.getPostJson());
		return model;
	}

	@Override
	protected Function<RemoteJob, RemoteJobEditDto> taskMapper() {
		return model -> {
			RemoteJobEditDto dto = new RemoteJobEditDto();
			dto.setRemoteJobId(model.getId());
			dto.setRequestUrl(model.getRequestUrl());
			dto.setRequestMethod(model.getRequestMethod());
			dto.setAuthString(model.getAuthString());
			dto.setPostJson(model.getPostJson());
			return dto;
		};
	}
}
