package scheduler.app.services.tasks;

import org.springframework.stereotype.Service;
import scheduler.app.models.TaskEntry;

import java.util.List;

@Service
public class TaskEntryImpl implements TaskEntryService {

	@Override
	public List<TaskEntry> loadAll() {
		return null;
	}

	@Override
	public TaskEntry load( final int taskId ) {
		return null;
	}

	@Override
	public TaskEntry delete( final int taskId ) {
		return null;
	}
}
