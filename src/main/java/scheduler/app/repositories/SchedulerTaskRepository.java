package scheduler.app.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entities.SchedulerTaskEntry;

@Repository
public interface SchedulerTaskRepository extends JpaRepository<SchedulerTaskEntry, Long> {

	String CACHE_ENTRY = "scheduler.app.cache.scheduler-task";
    String CACHE_QUERY = "scheduler.app.cache.scheduler-tasks";

	@Cacheable( value = CACHE_ENTRY, key = "#schedulerTaskId" )
	SchedulerTaskEntry findById(Long schedulerTaskId);
}
