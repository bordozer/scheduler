package scheduler.rest.common.routes;

import javax.naming.spi.StateFactory;

public interface Route {

    String SERVER = System.getProperty("host", "http://localhost:9097");

    String getRoute();

    static String buildRoute(final Route route) {
        return String.format("%s%s",SERVER, route.getRoute());
    }
}
