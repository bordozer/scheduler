package scheduler.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entries.SchedulerTaskEntry;

@Repository
public interface TaskRepository extends CrudRepository<SchedulerTaskEntry, Long> {

}
