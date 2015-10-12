package scheduler.app.dao;

import org.springframework.data.repository.CrudRepository;
import scheduler.app.models.TaskEntry;

public interface TaskRepository extends CrudRepository<TaskEntry, Long> {

}
