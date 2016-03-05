package scheduler.app.services.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import scheduler.app.converters.entity.UserEntityConverter;
import scheduler.app.dao.UserRepository;
import scheduler.app.models.User;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserEntityConverter userEntityConverter;

	@Override
	public User findByLogin(final String login) {
		return userEntityConverter.toModel(userRepository.findByLogin(login));
	}

	private String encodePassword(final String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
