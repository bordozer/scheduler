package scheduler.app.security;

import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import scheduler.app.services.users.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {

    @Inject
    private UserService userService;

    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                final Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
        scheduler.app.models.User user = userService.findByLogin(principal.getUsername());

        AuthResponse authResponse = new AuthResponse();
        authResponse.addParameters("auth_result", "Logged out");
        authResponse.addParameters("user_name", user.getUsername());


        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(authResponse));
        writer.flush();
    }
}
