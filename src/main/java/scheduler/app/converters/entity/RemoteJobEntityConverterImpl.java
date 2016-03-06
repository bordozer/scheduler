package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.models.RemoteJob;

@Service
public class RemoteJobEntityConverterImpl implements RemoteJobEntityConverter {

	private static final String ENTITY_MUST_NOT_BE_NULL = "Entity must not be null";
	private static final String USER_MUST_NOT_BE_NULL = "User must not be null";

	@Override
	public void populateEntity(final RemoteJobEntity entity, final RemoteJob model) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(model, USER_MUST_NOT_BE_NULL);

		entity.setId(model.getId());
		entity.setRequestUrl(model.getRequestUrl());
		entity.setRequestMethod(model.getRequestMethod());
		entity.setAuthString(model.getAuthString());
		entity.setPostJson(model.getPostJson());
	}

	@Override
	public RemoteJob toModel(final RemoteJobEntity entity) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);

		final RemoteJob model = new RemoteJob();
		model.setId(entity.getId());
		model.setRequestUrl(entity.getRequestUrl());
		model.setRequestMethod(entity.getRequestMethod());
		model.setAuthString(entity.getAuthString());
		model.setPostJson(entity.getPostJson());
		return model;
	}
}
