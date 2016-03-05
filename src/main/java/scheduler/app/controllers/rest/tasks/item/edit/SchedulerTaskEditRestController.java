package scheduler.app.controllers.rest.tasks.item.edit;

import org.springframework.web.bind.annotation.*;
import scheduler.app.converters.dto.SchedulerTaskEditDtoConverter;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;
import scheduler.app.services.tasks.SchedulerTaskService;
import scheduler.app.services.users.UserService;

import javax.inject.Inject;
import java.security.Principal;

@RestController
@RequestMapping("/rest/tasks")
public class SchedulerTaskEditRestController {

	@Inject
	private UserService userService;

	@Inject
	private SchedulerTaskService schedulerTaskService;

	@Inject
	private SchedulerTaskEditDtoConverter schedulerTaskEditDtoConverter;

	@RequestMapping(method = RequestMethod.PUT, value = "/0/")
	public SchedulerTaskEditDTO create(final @RequestBody SchedulerTaskEditDTO editDTO, final Principal principal) {
		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(getCurrentUser(principal.getName()), editDTO);
		SchedulerTask created = schedulerTaskService.add(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(created);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{taskId}/")
	public SchedulerTaskEditDTO edit(final @RequestBody SchedulerTaskEditDTO editDTO, final Principal principal) {
		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(getCurrentUser(principal.getName()), editDTO);
		SchedulerTask saved = schedulerTaskService.save(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
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
