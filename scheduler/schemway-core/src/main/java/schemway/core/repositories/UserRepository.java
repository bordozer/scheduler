package schemway.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<schemway.core.entities.UserEntity, Long> {

	String CACHE_ENTRY = "scheduler.app.cache.user";
    String CACHE_QUERY = "scheduler.app.cache.users";

//	@Cacheable( value = CACHE_ENTRY, key = "#userid" )
	schemway.core.entities.UserEntity findById(Long userId);

//	@Cacheable( value = CACHE_ENTRY, key = "#p0" )
	@Query(value = "select u from UserEntity u inner join u.secureDetails sd where u.secureDetails.login = :login")
	schemway.core.entities.UserEntity findByLogin(@Param("login") String login);

	/*@Caching( evict = {
			@CacheEvict( value = CACHE_ENTRY, key = "#p0.id" )
			, @CacheEvict( value = CACHE_QUERY, allEntries = true )
	} )
	@Override
	UserEntity saveAndFlush(UserEntity entity);*/
}
