package scheduler.app.controllers.rest.tasks.item.edit;

import org.springframework.web.bind.annotation.*;
import scheduler.app.converters.dto.SchedulerTaskDtoConverter;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;
import scheduler.app.services.tasks.SchedulerTaskService;
import scheduler.app.services.users.UserService;

import javax.inject.Inject;
import java.security.Principal;

@RestController
@RequestMapping("/rest/tasks")
public class TaskEditRestController {

	@Inject
	private UserService userService;

	@Inject
	private SchedulerTaskService schedulerTaskService;

	@Inject
	private SchedulerTaskDtoConverter schedulerTaskDtoConverter;

	@RequestMapping(method = RequestMethod.PUT, value = "/0/")
	public SchedulerTaskEditDTO create(final @RequestBody SchedulerTaskEditDTO editDTO, final Principal principal) {
		SchedulerTask schedulerTask = schedulerTaskDtoConverter.toModel(getCurrentUser(principal.getName()), editDTO);
		SchedulerTask created = schedulerTaskService.add(schedulerTask);
		return schedulerTaskDtoConverter.toEditDto(created);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{taskId}/")
	public SchedulerTaskEditDTO edit(final @RequestBody SchedulerTaskEditDTO editDTO, final Principal principal) {
		SchedulerTask schedulerTask = schedulerTaskDtoConverter.toModel(getCurrentUser(principal.getName()), editDTO);
		SchedulerTask saved = schedulerTaskService.save(schedulerTask);
		return schedulerTaskDtoConverter.toEditDto(saved);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taskId}/")
	public void delete(final @PathVariable int taskId) {
		if (taskId == 0) {
			return;
		}
		schedulerTaskService.delete(taskId);
	}

	private User getCurrentUser(final String login) {
		return userService.findByLogin(login);
	}
}
