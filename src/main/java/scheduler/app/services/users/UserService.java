package scheduler.app.services.users;

import scheduler.app.models.User;
import scheduler.app.models.UserSecureDetails;

public interface UserService {

	User findByLogin(String login);

	UserSecureDetails getUserSecureDetails(Long userId);
}
