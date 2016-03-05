package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserSecureDetails;
import scheduler.app.repositories.UserRepository;

import javax.inject.Inject;

@Service
public class UserSecureDetailsConverterImpl implements UserSecureDetailsConverter {

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserEntityConverter userEntityConverter;

	@Override
	public void populateEntity(final UserSecureDetailsEntity entity, final UserSecureDetails model) {
		entity.setId(model.getId());
		entity.setUser(userRepository.findById(model.getUser().getId()));
		entity.setLogin(model.getLogin());
		entity.setPassword(model.getPasswordEncrypted());
		entity.setRole(model.getRole());
	}

	@Override
	public UserSecureDetails toModel(final UserSecureDetailsEntity entity) {
		final UserSecureDetails model = new UserSecureDetails();
		model.setId(entity.getId());
		model.setUser(userEntityConverter.toModel(entity.getUser()));
		model.setLogin(entity.getLogin());
		model.setPasswordEncrypted(entity.getPassword());
		model.setRole(entity.getRole());
		return model;
	}
}
