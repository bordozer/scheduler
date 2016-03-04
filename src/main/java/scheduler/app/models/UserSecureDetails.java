package scheduler.app.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSecureDetails {
    private Long id;
    private Long userId;
    private String login;
    private String authString;
    private UserRole role;
}
