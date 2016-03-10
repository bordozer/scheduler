package scheduler.app.security;

import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        AuthResponse authResponse = new AuthResponse();
        authResponse.addParameters("auth_result", "Authentication failure");
        authResponse.addParameters("error", exception.getMessage());

        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(authResponse));
        writer.flush();
    }
}
