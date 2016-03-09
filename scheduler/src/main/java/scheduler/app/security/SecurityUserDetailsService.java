package scheduler.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scheduler.app.entities.UserEntity;
import scheduler.app.repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Inject
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

		final UserEntity user = userRepository.findByLogin(login);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Username not found: %s", login));
		}

		final List<GrantedAuthority> authorities = newArrayList();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new org.springframework.security.core.userdetails.User(login, user.getSecureDetails().getPassword(), authorities);
	}
}
