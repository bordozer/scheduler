package scheduler.app.services.users;

import org.junit.Test;
import scheduler.app.models.User;
import scheduler.app.models.UserRole;
import scheduler.app.models.UserSecureDetails;
import scheduler.app.repositories.AbstractIntegrationTest;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserServiceImplIntegrationTest extends AbstractIntegrationTest {

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
    public void shouldCreateUserDetailsWhenCreateNewUser() {
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

    @Test
    public void shouldUpdateNewlyCreatedUser() {
        User user = new User();
        user.setUsername(USER_NAME);

        UserSecureDetails details = new UserSecureDetails();
        details.setLogin(USER_LOGIN);
        details.setPasswordEncrypted(PASSWORD_NOT_ENCRYPTED); // TODO: should it be a separate model?
        details.setRole(UserRole.USER);
        details.setUser(user);

        User createdUser = sut.create(user, details);

        User loadedUser = sut.findByLogin(USER_LOGIN);
        loadedUser.setUsername("Updated user name");

        User modifiedUser = sut.modify(loadedUser);

        assertThat(modifiedUser, is(notNullValue()));
        assertThat(modifiedUser, is(createdUser));
        assertThat(modifiedUser.getId(), is(createdUser.getId()));
        assertThat(modifiedUser.getUsername(), is("Updated user name"));

        UserSecureDetails secureDetails = sut.getUserSecureDetails(createdUser.getId());
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getLogin(), is(USER_LOGIN));
        assertThat(secureDetails.getRole(), is(UserRole.USER));
        assertThat(secureDetails.getUser(), is(createdUser));
    }
}