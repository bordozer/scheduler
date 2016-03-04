package scheduler.app.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import scheduler.app.entries.UserEntry;
import scheduler.app.services.SystemVarsService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SystemVarsService systemVarsService;

    @Override
    public UserEntry findByLogin(final String login) {

        if (login.equals(systemVarsService.getRootUserName())) {
            final UserEntry userEntry = new UserEntry(systemVarsService.getRootUserName(), systemVarsService.getRootUserName(), encodePassword(systemVarsService.getRootUserPassword()));
            userEntry.setId(1);

            return userEntry;
        }

        return null;
    }

    private String encodePassword(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
