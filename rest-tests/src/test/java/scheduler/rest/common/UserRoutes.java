package scheduler.rest.common;

import lombok.Getter;

@Getter
public enum UserRoutes implements Route{
    USER_REGISTRATION("/rest/users/register/");

    private final String route;

    UserRoutes(final String route) {
        this.route = route;
    }
}
