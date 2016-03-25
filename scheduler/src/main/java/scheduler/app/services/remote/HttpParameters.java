package scheduler.app.services.remote;

import lombok.Getter;
import scheduler.app.models.RequestMethod;

import java.util.Map;

@Getter
public class HttpParameters {
    private final String requestUrl;
    private final RequestMethod requestMethod;
    private Map<String, String> requestParameters;
    private String json;

    public HttpParameters(final String requestUrl) {
        this.requestUrl = requestUrl;
        this.requestMethod = RequestMethod.GET;
    }

    public HttpParameters(final String requestUrl, final RequestMethod requestMethod) {
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
    }

    public HttpParameters setRequestParameters(final Map<String, String> requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    public HttpParameters setJson(final String json) {
        this.json = json;
        return this;
    }
}
