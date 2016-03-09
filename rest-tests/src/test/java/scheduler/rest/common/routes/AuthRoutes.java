package scheduler.rest.common.routes;

import lombok.Getter;

@Getter
public enum  AuthRoutes implements Route {
    LOGIN("/authenticate"),
    LOGOUT("/logout")
    ;

    private final String route;

    AuthRoutes(final String route) {
        this.route = route;
    }

    public String getRoute() {
        return null;
    }
}
