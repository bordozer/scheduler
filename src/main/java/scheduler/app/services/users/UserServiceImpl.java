package scheduler.app.services.users;

import org.springframework.beans.factory.annotation.Autowired;
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
			return new User( systemVarsService.getRootUserName(), systemVarsService.getRootUserName(), systemVarsService.getRootUserPassword() );
		}

		return null;
	}
}
