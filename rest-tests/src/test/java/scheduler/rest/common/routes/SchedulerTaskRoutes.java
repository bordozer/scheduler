package scheduler.rest.common.routes;

import lombok.Getter;

@Getter
public enum SchedulerTaskRoutes implements Route {
    SCHEDULER_TASK_LIST("/rest/tasks/");

    private final String route;

    SchedulerTaskRoutes(final String route) {
        this.route = route;
    }
}
