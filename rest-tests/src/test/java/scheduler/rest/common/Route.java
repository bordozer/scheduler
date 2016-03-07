package scheduler.rest.common;

public interface Route {
    String server = System.getProperty("host", "http://localhost:9097");

    static String buildRoute(Route route) {
        return String.join("", server, route.getRoute());
    }

    String getRoute();
}
