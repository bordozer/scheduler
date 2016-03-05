package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entries.SchedulerTaskEntry;

@Repository
public interface TaskRepository extends JpaRepository<SchedulerTaskEntry, Long> {

	SchedulerTaskEntry findById(Long id);

}
