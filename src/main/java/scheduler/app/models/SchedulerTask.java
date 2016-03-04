package scheduler.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SchedulerTask implements Model {
    private Long id;
    private User user;
    private String taskName;
    private String taskDescription;
}
