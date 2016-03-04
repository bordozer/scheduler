package scheduler.app.controllers.rest.tasks.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulerTaskDTO {
    private long taskId;
    private String taskName;
    private String taskDescription;
    private String taskParametersJSON;
    private String remoteURL;
    private String remoteParametersJSON;
}
