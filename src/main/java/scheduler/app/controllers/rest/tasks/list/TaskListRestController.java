package scheduler.app.controllers.rest.tasks.list;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.converters.dto.SchedulerTaskDtoConverter;
import scheduler.app.dto.IdDto;
import scheduler.app.dto.SchedulerTaskDto;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/tasks")
public class TaskListRestController {

	@Inject
	private SchedulerTaskService schedulerTaskService;

	@Inject
	private SchedulerTaskDtoConverter schedulerTaskDtoConverter;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<SchedulerTaskDto> tasks() {
		return schedulerTaskDtoConverter.toDtos(schedulerTaskService.loadAll());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/ids/")
	public List<IdDto> taskIds() {
		return schedulerTaskService.loadAll()
				.stream()
				.map(schedulerTaskEntry -> new IdDto(schedulerTaskEntry.getId()))
				.collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{taskId}/")
	public SchedulerTaskDto taskEntry(final @PathVariable int taskId) {
		return schedulerTaskDtoConverter.toDto(schedulerTaskService.load(taskId));
	}
}
