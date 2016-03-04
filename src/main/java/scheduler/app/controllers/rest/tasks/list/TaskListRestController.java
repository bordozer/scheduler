package scheduler.app.controllers.rest.tasks.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.services.DTOService;
import scheduler.app.services.tasks.TaskService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/tasks")
public class TaskListRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private DTOService dtoService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<TaskEntryDTO> tasks() {
        return dtoService.transformTasks(taskService.loadAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ids/")
    public List<IdDTO> taskIds() {

        return taskService.loadAll()
                .stream()
                .map(new Function<SchedulerTaskEntry, IdDTO>() {
                    @Override
                    public IdDTO apply(final SchedulerTaskEntry schedulerTaskEntry) {
                        return new IdDTO(schedulerTaskEntry.getId());
                    }
                })
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{taskId}/")
    public TaskEntryDTO taskEntry(final @PathVariable int taskId) {
        return dtoService.transformTask(taskService.load(taskId));
    }
}
