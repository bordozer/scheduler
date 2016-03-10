package scheduler.rest.dto;

public class SchedulerTaskDto {
    private Long id;
    private UserDto user;
    private SchedulerTaskType taskType;
    private String taskName;
    private String taskDescription;
    private String taskParametersJSON;
    private RemoteJobDto remoteJob;

    @Override
    public String toString() {
        return String.format("#%s, %s, %s, %s", id, user, taskName, taskType);
    }
}
