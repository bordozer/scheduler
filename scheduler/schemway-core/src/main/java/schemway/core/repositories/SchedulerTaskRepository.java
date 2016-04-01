package schemway.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SchedulerTaskRepository extends JpaRepository<schemway.core.entities.SchedulerTaskEntity, Long> {

	String CACHE_ENTRY = "scheduler.app.cache.scheduler-task";
	String CACHE_QUERY = "scheduler.app.cache.scheduler-tasks";

//	@Cacheable(value = CACHE_ENTRY, key = "#schedulerTaskId")
	schemway.core.entities.SchedulerTaskEntity findByUserIdAndId(Long userId, Long schedulerTaskId);

//	@Cacheable(value = CACHE_QUERY)
	List<schemway.core.entities.SchedulerTaskEntity> findAllByUserId(Long schedulerTaskId);

	@Transactional
	List<schemway.core.entities.SchedulerTaskEntity> deleteAllByUserIdAndId(Long userId, Long schedulerTaskId);

//	@Caching(evict = {
//			@CacheEvict(value = CACHE_ENTRY, key = "#entity.id")
//			, @CacheEvict(value = CACHE_QUERY, allEntries = true)
//	})
	@Transactional
	@Override
	schemway.core.entities.SchedulerTaskEntity saveAndFlush(schemway.core.entities.SchedulerTaskEntity entity);
}
