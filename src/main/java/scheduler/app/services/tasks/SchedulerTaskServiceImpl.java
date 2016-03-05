package scheduler.app.services.tasks;

import org.springframework.stereotype.Service;
import scheduler.app.converters.entity.SchedulerTaskEntityConverter;
import scheduler.app.dao.TaskRepository;
import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.models.SchedulerTask;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskServiceImpl implements SchedulerTaskService {

	@Inject
	private TaskRepository taskRepository;

	@Inject
	private SchedulerTaskEntityConverter schedulerTaskEntityConverter;

	@Override
	public List<SchedulerTask> loadAll() {
		return taskRepository.findAll().stream()
				.map(entity -> schedulerTaskEntityConverter.toModel(entity))
				.collect(Collectors.toList());
	}

	@Override
	public SchedulerTask load(final long taskId) {
		return schedulerTaskEntityConverter.toModel(taskRepository.findOne(taskId));
	}

	@Override
	public SchedulerTask add(final SchedulerTask schedulerTask) {
		return populateAndSave(schedulerTask, new SchedulerTaskEntry());
	}

	@Override
	public SchedulerTask save(final SchedulerTask schedulerTask) {
		return populateAndSave(schedulerTask, taskRepository.findById(schedulerTask.getId()));
	}

	@Override
	public void delete(final long taskId) {
		taskRepository.delete(taskId);
	}

	private SchedulerTask populateAndSave(final SchedulerTask schedulerTask, final SchedulerTaskEntry dbEntry) {
		schedulerTaskEntityConverter.populateEntity(dbEntry, schedulerTask);
		SchedulerTaskEntry savedEntity = taskRepository.save(dbEntry);
		return schedulerTaskEntityConverter.toModel(savedEntity);
	}
}
