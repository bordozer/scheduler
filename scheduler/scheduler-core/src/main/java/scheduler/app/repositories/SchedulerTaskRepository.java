package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import scheduler.app.entities.SchedulerTaskEntity;

import java.util.List;

@Repository
public interface SchedulerTaskRepository extends JpaRepository<SchedulerTaskEntity, Long> {

	String CACHE_ENTRY = "scheduler.app.cache.scheduler-task";
	String CACHE_QUERY = "scheduler.app.cache.scheduler-tasks";

//	@Cacheable(value = CACHE_ENTRY, key = "#schedulerTaskId")
	SchedulerTaskEntity findByUserIdAndId(Long userId, Long schedulerTaskId);

//	@Cacheable(value = CACHE_QUERY)
	List<SchedulerTaskEntity> findAllByUserId(Long schedulerTaskId);

	@Transactional
	List<SchedulerTaskEntity> deleteAllByUserIdAndId(Long userId, Long schedulerTaskId);

//	@Caching(evict = {
//			@CacheEvict(value = CACHE_ENTRY, key = "#entity.id")
//			, @CacheEvict(value = CACHE_QUERY, allEntries = true)
//	})
	@Transactional
	@Override
	SchedulerTaskEntity saveAndFlush(SchedulerTaskEntity entity);
}
