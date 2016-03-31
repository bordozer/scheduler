package scheduler.web.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class AuthResponse {

    public static final String AUTH_RESULT = "auth_result";
    public static final String USER_NAME = "user_name";
    public static final String USER_ROLE = "user_role";
    public static final String ERROR = "error";

    private final int responseCode;
    private final Map<String, String> details;
}
