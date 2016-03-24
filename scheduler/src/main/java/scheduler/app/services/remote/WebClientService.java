package scheduler.app.services.remote;

import java.io.IOException;

public interface WebClientService {

    void sendGet(String url) throws IOException;

    void sendPost(String url, String json) throws Exception;
}
