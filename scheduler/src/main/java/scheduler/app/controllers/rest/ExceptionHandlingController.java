package scheduler.app.controllers.rest;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.exceptions.FieldErrorResource;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handleException(final Exception e, final Writer writer) throws IOException {
        List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        List<FieldErrorResource> response = fieldErrors.stream()
                .map(fieldError -> new FieldErrorResource(
                        fieldError.getField(),
                        fieldError.getRejectedValue().toString(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList()
                );
        final Map<String, List<FieldErrorResource>> fields = response.stream()
                .collect(Collectors.groupingBy(FieldErrorResource::getField));

        writer.write(new Gson().toJson(new ResponseExceptionsHolder(fields)));
    }

    /*@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public void permissionDenied(final Exception e, final Writer writer) throws IOException {
        writer.write();
    }*/

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class ResponseExceptionsHolder {
        Map<String, List<FieldErrorResource>> errorsMap;
    }
}
