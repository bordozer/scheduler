package scheduler.app.dao;

import scheduler.app.entries.UserEntry;

public interface UserDao {

    String CACHE_ENTRY = "scheduler.app.cache.user";
    String CACHE_QUERY = "scheduler.app.cache.users";

    UserEntry findByLogin(final String login);
}
