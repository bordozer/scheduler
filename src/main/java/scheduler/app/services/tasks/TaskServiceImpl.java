package scheduler.app.services.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduler.app.dao.TaskRepository;
import scheduler.app.entries.SchedulerTaskEntry;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<SchedulerTaskEntry> loadAll() {
        return newArrayList(taskRepository.findAll());
    }

    @Override
    public SchedulerTaskEntry load(final long taskId) {
        return taskRepository.findOne(taskId);
    }

    @Override
    public SchedulerTaskEntry save(final SchedulerTaskEntry schedulerTaskEntry) {
        return taskRepository.save(schedulerTaskEntry);
    }

    @Override
    public void delete(final long taskId) {
        taskRepository.delete(load(taskId));
    }
}
