package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.models.RemoteJob;

@Service
public class RemoteJobEntityConverterImpl implements RemoteJobEntityConverter {

	@Override
	public void populateEntity(final RemoteJobEntity entity, final RemoteJob model) {
		entity.setId(model.getId());
		entity.setUrl(model.getUrl());
		entity.setRequestMethod(model.getRequestMethod());
		entity.setAuthString(model.getAuthString());
		entity.setPostJson(model.getPostJson());
	}

	@Override
	public RemoteJob toModel(final RemoteJobEntity entity) {
		final RemoteJob model = new RemoteJob();
		model.setId(entity.getId());
		model.setUrl(entity.getUrl());
		model.setRequestMethod(entity.getRequestMethod());
		model.setAuthString(entity.getAuthString());
		model.setPostJson(entity.getPostJson());
		return model;
	}
}
