package scheduler.app.services.users;

import org.springframework.transaction.annotation.Transactional;
import scheduler.app.models.User;
import scheduler.app.models.UserSecureDetails;

public interface UserService {

	User findByLogin(String login);

	@Transactional
	User save(User user);

	@Transactional
	User save(Long userId, UserSecureDetails userSecureDetails);

	UserSecureDetails getUserSecureDetails(Long userId);
}
