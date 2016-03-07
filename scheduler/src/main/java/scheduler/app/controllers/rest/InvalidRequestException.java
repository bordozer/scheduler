package scheduler.app.controllers.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

@Getter
@Setter
@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(final String message, final Errors errors) {
        super(message);
        this.errors = errors;
    }
}
