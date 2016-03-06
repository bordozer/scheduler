package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserSecureDetails;
import scheduler.app.repositories.UserRepository;

import javax.inject.Inject;

@Service
public class UserSecureDetailsConverterImpl implements UserSecureDetailsConverter {

	private static final String ENTITY_MUST_NOT_BE_NULL = "Entity must not be null";
	private static final String MODEL_MUST_NOT_BE_NULL = "Model must not be null";
	private static final String USER_MUST_NOT_BE_NULL = "User must not be null";

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserEntityConverter userEntityConverter;

	@Override
	public void populateEntity(final UserSecureDetailsEntity entity, final UserSecureDetails model) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(model, MODEL_MUST_NOT_BE_NULL);
		Assert.notNull(model.getUser(), USER_MUST_NOT_BE_NULL);

		entity.setId(model.getId());
		entity.setUser(userRepository.findById(model.getUser().getId()));
		entity.setLogin(model.getLogin());
		entity.setPassword(model.getPasswordEncrypted());
		entity.setRole(model.getRole());
	}

	@Override
	public UserSecureDetails toModel(final UserSecureDetailsEntity entity) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(entity.getUser(), USER_MUST_NOT_BE_NULL);

		final UserSecureDetails model = new UserSecureDetails();
		model.setId(entity.getId());
		model.setUser(userEntityConverter.toModel(entity.getUser()));
		model.setLogin(entity.getLogin());
		model.setPasswordEncrypted(entity.getPassword());
		model.setRole(entity.getRole());
		return model;
	}
}
