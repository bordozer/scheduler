package schemway.scheduler.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import org.quartz.JobDetail;
import org.quartz.Trigger;

@Getter
@Setter
@Builder
public class SchedulerJobModel {
    private Long schedulerTaskId;
    private JobDetail jobDetail;
    private Trigger trigger;
}
