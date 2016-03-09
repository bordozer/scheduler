package scheduler.rest.common.routes;

import lombok.Getter;
import scheduler.rest.common.routes.Route;

@Getter
public enum UserRoutes implements Route {
    USER_REGISTRATION("/rest/users/register/");

    private final String route;

    UserRoutes(final String route) {
        this.route = route;
    }
}
