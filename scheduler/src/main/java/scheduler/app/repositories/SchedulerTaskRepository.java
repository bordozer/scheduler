package scheduler.app.repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entities.SchedulerTaskEntity;

import java.util.List;

@Repository
public interface SchedulerTaskRepository extends JpaRepository<SchedulerTaskEntity, Long> {

	String CACHE_ENTRY = "scheduler.app.cache.scheduler-task";
	String CACHE_QUERY = "scheduler.app.cache.scheduler-tasks";

	@Cacheable(value = CACHE_ENTRY, key = "#schedulerTaskId")
	SchedulerTaskEntity findById(Long schedulerTaskId);

	@Cacheable(value = CACHE_QUERY)
	List<SchedulerTaskEntity> findAllByUserId(Long schedulerTaskId);

	@Caching(evict = {
			@CacheEvict(value = CACHE_ENTRY, key = "#entity.id")
			, @CacheEvict(value = CACHE_QUERY, allEntries = true)
	})
	@Override
	SchedulerTaskEntity saveAndFlush(SchedulerTaskEntity entity);
}
