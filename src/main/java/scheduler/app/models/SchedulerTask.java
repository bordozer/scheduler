package scheduler.app.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulerTask {
    private Long id;
    private Long userId;
    private String taskName;
    private String taskDescription;
}
