package scheduler.app.dao;

import scheduler.app.models.User;

public interface UserDao {

	String CACHE_ENTRY = "scheduler.app.cache.user";
	String CACHE_QUERY = "scheduler.app.cache.users";

	User findByLogin( final String login );
}
