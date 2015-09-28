package scheduler.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduler.app.models.TaskEntry;

public interface TaskDao extends JpaRepository<TaskEntry, Long> {

}
