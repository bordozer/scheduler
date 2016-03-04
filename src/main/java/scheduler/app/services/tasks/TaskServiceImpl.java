package scheduler.app.services.tasks;

import org.springframework.stereotype.Service;
import scheduler.app.converters.entity.SchedulerTaskConverter;
import scheduler.app.dao.TaskRepository;
import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.models.SchedulerTask;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private SchedulerTaskConverter schedulerTaskConverter;

    @Override
    public List<SchedulerTask> loadAll() {
        return taskRepository.findAll().stream()
                .map(entity -> schedulerTaskConverter.toModel(entity))
                .collect(Collectors.toList());
    }

    @Override
    public SchedulerTask load(final long taskId) {
        return schedulerTaskConverter.toModel(taskRepository.findOne(taskId));
    }

    @Override
    public SchedulerTask save(final SchedulerTaskEntry schedulerTaskEntry) {
        return schedulerTaskConverter.toModel(taskRepository.save(schedulerTaskEntry));
    }

    @Override
    public void delete(final long taskId) {
        taskRepository.delete(taskId);
    }
}
