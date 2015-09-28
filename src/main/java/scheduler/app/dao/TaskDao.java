package scheduler.app.dao;

import org.springframework.data.repository.Repository;
import scheduler.app.models.TaskEntry;

public interface TaskDao extends Repository<TaskEntry, Long> {

}
