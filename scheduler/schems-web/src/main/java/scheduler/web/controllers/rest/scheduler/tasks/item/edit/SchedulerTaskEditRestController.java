package scheduler.web.controllers.rest.scheduler.tasks.item.edit;

import org.springframework.web.bind.annotation.*;
import scheduler.core.models.SchedulerTask;
import scheduler.core.models.User;
import scheduler.core.services.users.UserService;
import scheduler.core.services.tasks.SchedulerTaskService;
import scheduler.web.controllers.rest.scheduler.tasks.item.edit.converters.SchedulerTaskEditDtoConverter;
import scheduler.web.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.web.controllers.rest.scheduler.tasks.item.edit.services.SchedulerTaskEditService;

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
	public SchedulerTaskEditDto getForEdit(final @PathVariable Long schedulerTaskId,
										   final Principal principal) {
		User currentUser = getCurrentUser(principal.getName());
		SchedulerTask schedulerTask = schedulerTaskService.load(currentUser.getId(), schedulerTaskId);
		return schedulerTaskEditDtoConverter.toDto(schedulerTask);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/0/")
	public SchedulerTaskEditDto create(final @RequestBody SchedulerTaskEditDto editDTO,
									   final Principal principal) {
		return schedulerTaskEditService.create(getCurrentUser(principal.getName()), editDTO);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{schedulerTaskId}/")
	public SchedulerTaskEditDto modify(final @PathVariable Long schedulerTaskId,
									   final @RequestBody SchedulerTaskEditDto editDTO,
									   final Principal principal) {
		return schedulerTaskEditService.modify(getCurrentUser(principal.getName()), editDTO);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{schedulerTaskId}/")
	public void delete(final @PathVariable Long schedulerTaskId, final Principal principal) {
		schedulerTaskEditService.delete(getCurrentUser(principal.getName()), schedulerTaskId);
	}

	private User getCurrentUser(final String login) {
		return userService.findByLogin(login);
	}
}
