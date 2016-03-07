package scheduler.rest.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExceptionsHolder {

    Map<String, List<FieldErrorResource>> errorsMap;

    public List<FieldErrorResource> getFieldError(final String field) {
        return errorsMap.get(field);
    }

    public int errorsCount() {
        if (errorsMap == null || errorsMap.size() == 0) {
            return 0;
        }

        final List<Integer> collect = errorsMap.keySet().stream()
                .map(field -> errorsMap.get(field).size()).collect(Collectors.toList());
        return collect.stream().mapToInt(Integer::intValue).sum();
    }
}
