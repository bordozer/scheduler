package scheduler.app.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import scheduler.app.models.User;
import scheduler.app.services.SystemVarsService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SystemVarsService systemVarsService;

	@Override
	public User findByLogin( final String login ) {

		if ( login.equals( systemVarsService.getRootUserName() ) ) {
			final User user = new User( systemVarsService.getRootUserName(), systemVarsService.getRootUserName(), encodePassword( systemVarsService.getRootUserPassword() ) );
			user.setId( 1 );

			return user;
		}

		return null;
	}

	private String encodePassword( final String password ) {
		return new BCryptPasswordEncoder().encode( password );
	}
}
