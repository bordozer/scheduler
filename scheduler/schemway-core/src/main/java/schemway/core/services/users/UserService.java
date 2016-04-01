package schemway.core.services.users;

import org.springframework.transaction.annotation.Transactional;
import schemway.core.models.User;
import schemway.core.models.UserSecureDetails;

public interface UserService {

	User findByLogin(String login);

	@Transactional
	User create(User user, UserSecureDetails userSecureDetails);

	@Transactional
	User modify(User user);

	UserSecureDetails loadUserSecureDetails(Long userId);
}
