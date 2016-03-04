package scheduler.app.controllers.rest.tasks.list;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.converters.dto.DTOService;
import scheduler.app.services.tasks.TaskService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/tasks")
public class TaskListRestController {

    @Inject
    private TaskService taskService;

    @Inject
    private DTOService dtoService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<SchedulerTaskDTO> tasks() {
        return dtoService.transformTasks(taskService.loadAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ids/")
    public List<IdDTO> taskIds() {
        return taskService.loadAll()
                .stream()
                .map(schedulerTaskEntry -> new IdDTO(schedulerTaskEntry.getId()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{taskId}/")
    public SchedulerTaskDTO taskEntry(final @PathVariable int taskId) {
        return dtoService.transformTask(taskService.load(taskId));
    }
}
