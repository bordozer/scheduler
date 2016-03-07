package scheduler.rest.dto;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import scheduler.rest.dto.UserDto;

import java.util.List;

@Getter
@Setter
public class RegistrationResponse {
    private boolean success;
    private UserDto user;
    private List<String> errors = Lists.newArrayList();
}
