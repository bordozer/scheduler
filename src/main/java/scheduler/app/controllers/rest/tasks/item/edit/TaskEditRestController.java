package scheduler.app.controllers.rest.tasks.item.edit;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.converters.dto.SchedulerTaskDtoConverter;
import scheduler.app.entries.SchedulerTaskEntry;
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
        SchedulerTask schedulerTask = schedulerTaskDtoConverter.toModel(getCurrentUser(principal), editDTO);
        SchedulerTask createdEntry = schedulerTaskService.add(schedulerTask);
        return schedulerTaskDtoConverter.toEditDto(createdEntry);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{taskId}/")
    public SchedulerTaskEditDTO edit(final @RequestBody SchedulerTaskEditDTO editDTO, final Principal principal) {
        SchedulerTask schedulerTask = schedulerTaskDtoConverter.toModel(getCurrentUser(principal), editDTO);
        SchedulerTask createdEntry = schedulerTaskService.save(schedulerTask);
        return schedulerTaskDtoConverter.toEditDto(createdEntry);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{taskId}/")
    public void delete(final @PathVariable int taskId) {

        if (taskId == 0) {
            return;
        }

        schedulerTaskService.delete(taskId);
    }

    private User getCurrentUser(final Principal principal) {
        return userService.findByLogin(principal.getName());
    }
}
