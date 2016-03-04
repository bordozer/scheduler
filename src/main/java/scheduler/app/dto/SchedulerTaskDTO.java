package scheduler.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulerTaskDTO implements DTO {
    private long taskId;
    private String taskName;
    private String taskDescription;
    private String taskParametersJSON;
    private String remoteURL;
    private String remoteParametersJSON;
}
