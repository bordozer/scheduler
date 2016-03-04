package scheduler.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.entries.UserEntry;

@Repository
public interface UserRepository extends CrudRepository<SchedulerTaskEntry, Long> {
//    String CACHE_ENTRY = "scheduler.app.cache.user";
//    String CACHE_QUERY = "scheduler.app.cache.users";

    UserEntry findById(final Long userId);

    UserEntry findByLogin(final String login);
}
