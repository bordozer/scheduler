package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.entries.UserEntry;

@Repository
public interface UserRepository extends JpaRepository<SchedulerTaskEntry, Long> {
//    String CACHE_ENTRY = "scheduler.app.cache.user";
//    String CACHE_QUERY = "scheduler.app.cache.users";

	UserEntry findById(final Long userId);

	UserEntry findByLogin(final String login);
}
