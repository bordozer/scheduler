package scheduler.rest.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExceptionsHolder {
    List<FieldErrorResource> errors;

    public FieldErrorResource getFieldError(final String field) {
        return errors.stream()
                .filter(fieldError -> fieldError.getField().equalsIgnoreCase(field))
                .findFirst()
                .orElseGet(null);
    }

    public int errorsCount() {
        return Optional.ofNullable(errors).map(List::size).orElse(0);
    }
}
