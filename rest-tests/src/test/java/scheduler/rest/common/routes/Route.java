package scheduler.rest.common.routes;

public interface Route {

    String SERVER = System.getProperty("host", "http://localhost:9097");

    static String buildRoute(final Route route) {
        return String.join("", SERVER, route.getRoute());
    }

    String getRoute();
}
