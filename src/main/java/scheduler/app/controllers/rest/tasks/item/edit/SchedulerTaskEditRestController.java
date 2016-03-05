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
	public SchedulerTaskEditDto create(final @RequestBody SchedulerTaskEditDto editDTO, final Principal principal) {
		final User currentUser = getCurrentUser(principal.getName());
		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(currentUser, editDTO);
		SchedulerTask created = schedulerTaskService.add(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(created);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{taskId}/")
	public SchedulerTaskEditDto edit(final @RequestBody SchedulerTaskEditDto editDTO, final Principal principal) {
		final User currentUser = getCurrentUser(principal.getName());
		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(currentUser, editDTO);
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
