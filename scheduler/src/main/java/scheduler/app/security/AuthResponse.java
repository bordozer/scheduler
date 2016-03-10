package scheduler.app.security;

import com.beust.jcommander.internal.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class AuthResponse {
    private final Map<String, String> map = Maps.newLinkedHashMap();

    public void addParameters(final String key, final String value) {
        map.put(key, value);
    }
}
