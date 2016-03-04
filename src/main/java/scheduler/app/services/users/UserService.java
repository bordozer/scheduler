package scheduler.app.services.users;

import scheduler.app.entries.UserEntry;

public interface UserService {

    UserEntry findByLogin(final String login);
}
