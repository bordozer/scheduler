package scheduler.rest.common.routes;

import lombok.Getter;
import scheduler.rest.common.routes.Route;

@Getter
public enum  AuthRoutes implements Route {
    LOGIN("/authenticate");

    private final String route;

    AuthRoutes(final String route) {
        this.route = route;
    }

    public String getRoute() {
        return null;
    }
}
