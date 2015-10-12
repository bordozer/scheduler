package scheduler.app.controllers.rest.tasks.item.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scheduler.app.models.TaskEntry;
import scheduler.app.services.tasks.TaskService;

@RestController
@RequestMapping( "/rest/tasks" )
public class TaskEditRestController {

	@Autowired
	private TaskService taskService;

	@RequestMapping( method = RequestMethod.PUT, value = "/0/" )
	public TaskEntryEditDTO create( final @RequestBody TaskEntryEditDTO dto ) {
		return toDTO( taskService.save( fromDTO( new TaskEntry(), dto ) ) );
	}

	@RequestMapping( method = RequestMethod.PUT, value = "/{taskId}/" )
	public TaskEntryEditDTO edit( final @RequestBody TaskEntryEditDTO dto ) {
		return toDTO( taskService.save( fromDTO( taskService.load( dto.getTaskId() ), dto ) ) );
	}

	@RequestMapping( method = RequestMethod.DELETE, value = "/{taskId}/" )
	public void delete( final @PathVariable int taskId ) {

		if ( taskId == 0 ) {
			return;
		}

		taskService.delete( taskId );
	}

	private TaskEntryEditDTO toDTO( final TaskEntry task ) {

		final TaskEntryEditDTO dto = new TaskEntryEditDTO();
		dto.setTaskId( task.getId() );
		dto.setTaskName( task.getTaskName() );
		// TODO: init DTO from task

		return dto;
	}

	private TaskEntry fromDTO( final TaskEntry entry, final TaskEntryEditDTO dto ) {

		entry.setTaskName( dto.getTaskName() );
		// TODO: init task from DTO

		return entry;
	}
}
