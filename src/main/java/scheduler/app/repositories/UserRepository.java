package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scheduler.app.entities.UserEntry;



@Repository
public interface UserRepository extends JpaRepository<UserEntry, Long> {
//    String CACHE_ENTRY = "scheduler.app.cache.user";
//    String CACHE_QUERY = "scheduler.app.cache.users";

	UserEntry findById(Long userId);

	@Query(value = UserEntry.NAMED_QUERY_FIND_BY_LOGIN)
	UserEntry findByLogin(@Param("login") String login);
}
