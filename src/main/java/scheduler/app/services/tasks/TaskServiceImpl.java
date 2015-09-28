package scheduler.app.services.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduler.app.dao.TaskDao;
import scheduler.app.models.TaskEntry;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public List<TaskEntry> loadAll() {
		return taskDao.findAll();
	}

	@Override
	public TaskEntry load( final long taskId ) {
		return taskDao.findOne( taskId );
	}

	@Override
	public void delete( final long taskId ) {
		taskDao.delete( load( taskId ) );
	}
}
