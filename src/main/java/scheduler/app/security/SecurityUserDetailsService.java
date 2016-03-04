package scheduler.app.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scheduler.app.entries.UserEntry;
import scheduler.app.models.User;
import scheduler.app.services.users.UserService;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SecurityUserDetailsService.class);

    @Inject
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

        final User user = userService.findByLogin(login);

        if (user == null) {
            LOGGER.debug(String.format("================================= User login not found: %s =================================", login));

            throw new UsernameNotFoundException(String.format("Username not found: %s", login));
        }

        final List<GrantedAuthority> authorities = newArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(login, user.getSecureDetails().getPassword(), authorities);
    }
}
