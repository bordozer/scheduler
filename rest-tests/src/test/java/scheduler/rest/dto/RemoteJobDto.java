package scheduler.rest.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "remoteJobId")
public class RemoteJobDto {
    private Long remoteJobId;
    private String requestUrl;
    private RequestMethod requestMethod;
    private String authString;
    private String postJson;

    @Override
    public String toString() {
        return String.format("#%d: %s (%s)", remoteJobId, requestUrl, requestMethod);
    }
}
