package scheduler.app.services.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduler.app.dao.TaskRepository;
import scheduler.app.entries.TaskEntry;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntry> loadAll() {
        return newArrayList(taskRepository.findAll());
    }

    @Override
    public TaskEntry load(final long taskId) {
        return taskRepository.findOne(taskId);
    }

    @Override
    public TaskEntry save(final TaskEntry taskEntry) {
        return taskRepository.save(taskEntry);
    }

    @Override
    public void delete(final long taskId) {
        taskRepository.delete(load(taskId));
    }
}
