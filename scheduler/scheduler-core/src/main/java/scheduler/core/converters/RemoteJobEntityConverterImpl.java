package scheduler.core.converters;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RemoteJobEntityConverterImpl implements RemoteJobEntityConverter {

	private static final String ENTITY_MUST_NOT_BE_NULL = "Entity must not be null";
	private static final String USER_MUST_NOT_BE_NULL = "User must not be null";

	@Override
	public void populateEntity(final scheduler.core.entities.RemoteJobEntity entity, final scheduler.core.models.RemoteJob model) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(model, USER_MUST_NOT_BE_NULL);

		entity.setId(model.getId());
		entity.setRequestUrl(model.getRequestUrl());
		entity.setRequestMethod(model.getRequestMethod());
		entity.setAuthString(model.getAuthString());
		entity.setPostJson(model.getPostJson());
	}

	@Override
	public scheduler.core.models.RemoteJob toModel(final scheduler.core.entities.RemoteJobEntity entity) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);

		final scheduler.core.models.RemoteJob model = new scheduler.core.models.RemoteJob();
		model.setId(entity.getId());
		model.setRequestUrl(entity.getRequestUrl());
		model.setRequestMethod(entity.getRequestMethod());
		model.setAuthString(entity.getAuthString());
		model.setPostJson(entity.getPostJson());
		return model;
	}
}
