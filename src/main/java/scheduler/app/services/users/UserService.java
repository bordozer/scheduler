package scheduler.app.services.users;

import scheduler.app.entries.UserEntry;
import scheduler.app.models.User;

public interface UserService {

    User findByLogin(final String login);
}
