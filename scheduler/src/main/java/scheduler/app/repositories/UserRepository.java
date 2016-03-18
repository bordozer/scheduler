package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scheduler.app.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	String CACHE_ENTRY = "scheduler.app.cache.user";
    String CACHE_QUERY = "scheduler.app.cache.users";

//	@Cacheable( value = CACHE_ENTRY, key = "#userid" )
	UserEntity findById(Long userId);

//	@Cacheable( value = CACHE_ENTRY, key = "#p0" )
	@Query(value = "select u from UserEntity u inner join u.secureDetails sd where u.secureDetails.login = :login")
	UserEntity findByLogin(@Param("login") String login);

	/*@Caching( evict = {
			@CacheEvict( value = CACHE_ENTRY, key = "#p0.id" )
			, @CacheEvict( value = CACHE_QUERY, allEntries = true )
	} )
	@Override
	UserEntity saveAndFlush(UserEntity entity);*/
}
