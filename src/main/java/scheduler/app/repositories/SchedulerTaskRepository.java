package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entities.SchedulerTaskEntry;

@Repository
public interface SchedulerTaskRepository extends JpaRepository<SchedulerTaskEntry, Long> {

	SchedulerTaskEntry findById(Long id);
}
