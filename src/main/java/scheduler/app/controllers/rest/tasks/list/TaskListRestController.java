package scheduler.app.controllers.rest.tasks.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.services.DTOService;
import scheduler.app.services.tasks.TaskEntryService;

import java.util.List;

@RestController
@RequestMapping( "/rest/tasks" )
public class TaskListRestController {

	@Autowired
	private TaskEntryService taskEntryService;

	@Autowired
	private DTOService dtoService;

	@RequestMapping( method = RequestMethod.GET, value = "/" )
	public List<TaskEntryDTO> tasks() {
		return dtoService.transformTasks( taskEntryService.loadAll() );
	}

	@RequestMapping( method = RequestMethod.GET, value = "/{taskId}/" )
	public TaskEntryDTO taskEntry( final @PathVariable int taskId ) {
		return dtoService.transformTask( taskEntryService.load( taskId ) );
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/{taskId}/" )
	public TaskEntryDTO delete( final @PathVariable int taskId ) {
		return dtoService.transformTask( taskEntryService.delete( taskId ) );
	}
}