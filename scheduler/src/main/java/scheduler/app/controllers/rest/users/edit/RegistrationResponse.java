package scheduler.app.controllers.rest.users.edit;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import scheduler.app.dto.UserDto;

import java.util.List;

@Getter
@Setter
public class RegistrationResponse {
    private boolean success;
    private UserDto user;
    private List<String> errors = Lists.newArrayList();

    public RegistrationResponse() {
    }

    public RegistrationResponse(final boolean success) {
        this.success = success;
    }
}
