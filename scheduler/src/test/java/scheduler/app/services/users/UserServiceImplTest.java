package scheduler.app.services.users;

import org.junit.Test;
import scheduler.app.models.User;
import scheduler.app.models.UserRole;
import scheduler.app.models.UserSecureDetails;
import scheduler.app.repositories.AbstractRepositoryTest;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserServiceImplTest extends AbstractRepositoryTest {

    private static final String USER_NAME = "User Name";
    private static final String USER_LOGIN = "login";
    private static final String PASSWORD_NOT_ENCRYPTED = "userpss";

    @Inject
    private UserService sut;

    @Test
    public void shouldCreateUserAndReturnModel() {
        User user = new User();
        user.setUsername(USER_NAME);

        UserSecureDetails details = new UserSecureDetails();
        details.setLogin(USER_LOGIN);
        details.setPasswordEncrypted("userpss");
        details.setRole(UserRole.USER);
        details.setUser(user);

        User createdUser = sut.create(user, details);
        assertThat(createdUser, is(notNullValue()));
        assertThat(createdUser.getUsername(), is(USER_NAME));
    }

    @Test
    public void shouldCreateUserDetails() {
        User user = new User();
        user.setUsername(USER_NAME);

        UserSecureDetails details = new UserSecureDetails();
        details.setLogin(USER_LOGIN);
        details.setPasswordEncrypted(PASSWORD_NOT_ENCRYPTED); // TODO: should it be a separate model?
        details.setRole(UserRole.USER);
        details.setUser(user);

        User createdUser = sut.create(user, details);

        UserSecureDetails secureDetails = sut.getUserSecureDetails(createdUser.getId());
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getLogin(), is(USER_LOGIN));
        assertThat(secureDetails.getRole(), is(UserRole.USER));
        assertThat(secureDetails.getUser(), is(createdUser));
    }
}