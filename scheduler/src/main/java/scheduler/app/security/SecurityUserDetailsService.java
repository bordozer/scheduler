package scheduler.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scheduler.app.models.User;
import scheduler.app.models.UserSecureDetails;
import scheduler.app.services.users.UserService;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Inject
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

		final User user = userService.findByLogin(login);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Username not found: %s", login));
		}

		UserSecureDetails userSecureDetails = userService.getUserSecureDetails(user.getId());

		final List<GrantedAuthority> authorities = newArrayList();
		authorities.add(new SimpleGrantedAuthority(userSecureDetails.getRole().getRole()));

		return new org.springframework.security.core.userdetails.User(login, userSecureDetails.getPasswordEncrypted(), authorities);
	}
}
