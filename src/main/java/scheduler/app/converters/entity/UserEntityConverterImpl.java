package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scheduler.app.entities.UserEntity;
import scheduler.app.models.User;

@Service
public class UserEntityConverterImpl implements UserEntityConverter {

	private static final String ENTITY_MUST_NOT_BE_NULL = "Entity must not be null";
	private static final String USER_MUST_NOT_BE_NULL = "User must not be null";

	@Override
	public void populateEntity(final UserEntity entity, final User user) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(user, USER_MUST_NOT_BE_NULL);

		entity.setId(user.getId());
		entity.setUsername(user.getUsername());
	}

	@Override
	public User toModel(final UserEntity entity) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);

		User model = new User();
		model.setId(entity.getId());
		model.setUsername(entity.getUsername());
		return model;
	}
}
