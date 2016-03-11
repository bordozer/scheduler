package scheduler.app.controllers.rest.scheduler.tasks.item.edit;

import org.springframework.web.bind.annotation.*;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.converters.SchedulerTaskEditDtoConverter;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.services.SchedulerTaskEditService;
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
	private SchedulerTaskEditService schedulerTaskEditService;

	@Inject
	private SchedulerTaskEditDtoConverter schedulerTaskEditDtoConverter;

	@RequestMapping(method = RequestMethod.PUT, value = "/{schedulerTaskId}/")
	public SchedulerTaskEditDto getForEdit(final @PathVariable Long schedulerTaskId, final Principal principal) {
		User currentUser = getCurrentUser(principal.getName());
		SchedulerTask schedulerTask = schedulerTaskService.load(currentUser.getId(), schedulerTaskId);
		return schedulerTaskEditDtoConverter.toDto(schedulerTask);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/0/")
	public SchedulerTaskEditDto create(final @RequestBody SchedulerTaskEditDto editDTO, final Principal principal) {
		return schedulerTaskEditService.create(getCurrentUser(principal.getName()), editDTO);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{taskId}/")
	public SchedulerTaskEditDto modify(final @RequestBody SchedulerTaskEditDto editDTO, final Principal principal) {
		return schedulerTaskEditService.modify(getCurrentUser(principal.getName()), editDTO);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taskId}/")
	public void delete(final @PathVariable Long taskId, final Principal principal) {
		schedulerTaskEditService.delete(getCurrentUser(principal.getName()), taskId);
	}

	private User getCurrentUser(final String login) {
		return userService.findByLogin(login);
	}
}
