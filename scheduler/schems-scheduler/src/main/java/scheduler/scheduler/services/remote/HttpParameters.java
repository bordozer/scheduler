package scheduler.scheduler.services.remote;

import lombok.Getter;
import scheduler.core.models.RequestMethod;

import java.util.Map;

@Getter
public class HttpParameters {
    private final String requestUrl;
    private final RequestMethod requestMethod;
    private Map<String, String> requestParameters;
    private String postJson;

    public HttpParameters(final String requestUrl) {
        this.requestUrl = requestUrl;
        this.requestMethod = RequestMethod.GET;
    }

    public HttpParameters(final String requestUrl, final RequestMethod requestMethod) {
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
    }

    public HttpParameters withRequestParameters(final Map<String, String> requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    public HttpParameters withPostJson(final String json) {
        this.postJson = json;
        return this;
    }
}
