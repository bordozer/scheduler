package scheduler.rest.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AuthResponse {
    public static final String AUTH_RESULT = "auth_result";
    public static final String USER_NAME = "user_name";
    public static final String USER_ROLE = "user_role";
    public static final String ERROR = "error";

    private int responseCode;
    private Map<String, String> details;
}
