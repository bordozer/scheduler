package schemway.core.services.users;

import org.springframework.stereotype.Service;
import schemway.core.converters.UserEntityConverter;
import schemway.core.converters.UserSecureDetailsConverter;
import schemway.core.entities.UserEntity;
import schemway.core.entities.UserSecureDetailsEntity;
import schemway.core.models.User;
import schemway.core.models.UserSecureDetails;
import schemway.core.repositories.UserRepository;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserEntityConverter userEntityConverter;

	@Inject
	private UserSecureDetailsConverter userSecureDetailsConverter;

	@Override
	public User findByLogin(final String login) {
		return userEntityConverter.toModel(userRepository.findByLogin(login));
	}

	@Override
	public User create(final User user, final UserSecureDetails userSecureDetails) {
		final UserEntity userEntity = new UserEntity();
		userEntityConverter.populateEntity(userEntity, user);

		UserSecureDetailsEntity userSecureDetailsEntity = new UserSecureDetailsEntity();
		userSecureDetailsEntity.setUser(userEntity);
		userSecureDetailsConverter.populateEntity(userSecureDetailsEntity, userSecureDetails);

		userEntity.setSecureDetails(userSecureDetailsEntity);

		final UserEntity saved = userRepository.saveAndFlush(userEntity);

		return userEntityConverter.toModel(saved);
	}

	@Override
	public User modify(final User user) {
		final UserEntity userEntity = userRepository.findById(user.getId());
		userEntityConverter.populateEntity(userEntity, user);
		final UserEntity saved = userRepository.saveAndFlush(userEntity);
		return userEntityConverter.toModel(saved);
	}

	@Override
	public UserSecureDetails loadUserSecureDetails(final Long userId) {
		return userSecureDetailsConverter.toModel(userRepository.findById(userId).getSecureDetails());
	}
}
