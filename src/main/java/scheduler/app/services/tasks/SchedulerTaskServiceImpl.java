package scheduler.app.services.tasks;

import org.springframework.stereotype.Service;
import scheduler.app.converters.entity.SchedulerTaskEntityConverter;
import scheduler.app.repositories.SchedulerTaskRepository;
import scheduler.app.entities.SchedulerTaskEntry;
import scheduler.app.models.SchedulerTask;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskServiceImpl implements SchedulerTaskService {

	@Inject
	private SchedulerTaskRepository schedulerTaskRepository;

	@Inject
	private SchedulerTaskEntityConverter schedulerTaskEntityConverter;

	@Override
	public List<SchedulerTask> loadAll() {
		return schedulerTaskRepository.findAll().stream()
				.map(schedulerTaskEntityConverter::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public SchedulerTask load(final long taskId) {
		return schedulerTaskEntityConverter.toModel(schedulerTaskRepository.findOne(taskId));
	}

	@Override
	public SchedulerTask add(final SchedulerTask schedulerTask) {
		return populateAndSave(schedulerTask, new SchedulerTaskEntry());
	}

	@Override
	public SchedulerTask save(final SchedulerTask schedulerTask) {
		return populateAndSave(schedulerTask, schedulerTaskRepository.findById(schedulerTask.getId()));
	}

	@Override
	public void delete(final long taskId) {
		schedulerTaskRepository.delete(taskId);
	}

	private SchedulerTask populateAndSave(final SchedulerTask schedulerTask, final SchedulerTaskEntry dbEntry) {
		schedulerTaskEntityConverter.populateEntity(dbEntry, schedulerTask);
		SchedulerTaskEntry savedEntity = schedulerTaskRepository.saveAndFlush(dbEntry);
		return schedulerTaskEntityConverter.toModel(savedEntity);
	}
}
