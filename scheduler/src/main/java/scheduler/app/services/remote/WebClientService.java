package scheduler.app.services.remote;

import java.io.IOException;

public interface WebClientService {

    void send(HttpParameters parameters) throws IOException;
}
