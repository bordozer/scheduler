package scheduler.app.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import scheduler.app.AbstractIntegrationTest;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserRole;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DatabaseSetup(value = UserRepositoryTest.TEST_DATA_SET, type = DatabaseOperation.INSERT)
@DatabaseTearDown(value = UserRepositoryTest.TEST_DATA_SET, type = DatabaseOperation.DELETE)
public class UserRepositoryTest extends AbstractIntegrationTest {

    public static final String TEST_DATA_SET = "classpath:/users/users.xml";

    private static final String NEW_USER_NAME = "Newly created user";
    private static final String NEW_USER_LOGIN = "new_user_login";
    private static final String NEW_USER_PASSWORD = "$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC";
    private static final UserRole NEW_USER_ROLE = UserRole.USER;

    @Inject
    private UserRepository sut;

    @Test
    public void shouldFindUser() {
        UserEntity userEntity1 = selectAndCheckUser(USER_CURRY, UserRole.ADMIN);
        UserSecureDetailsEntity secureDetails1 = userEntity1.getSecureDetails();
        assertThat(secureDetails1.getLogin(), is("curry"));
        assertThat(secureDetails1.getPassword(), is("$2a$10$oNXFlhGI0LekTBIedkILTeBYcDCT11Mb4NmIO.p5WH.6isYLLs9uC"));
    }

    @Test
    public void shouldFindUser2() {
        UserEntity userEntity2 = selectAndCheckUser(USER_IBAKA, NEW_USER_ROLE);
        UserSecureDetailsEntity secureDetails2 = userEntity2.getSecureDetails();
        assertThat(secureDetails2.getLogin(), is("ibaka"));
        assertThat(secureDetails2.getPassword(), is(NEW_USER_PASSWORD));
    }

    @Test
    public void shouldFindUser3() {
        UserEntity userEntity3 = selectAndCheckUser(USER_DURANT, NEW_USER_ROLE);
        UserSecureDetailsEntity secureDetails3 = userEntity3.getSecureDetails();
        assertThat(secureDetails3.getLogin(), is("durant"));
        assertThat(secureDetails3.getPassword(), is(NEW_USER_PASSWORD));
    }

    @Test
    public void shouldCreateNewUserWithoutUserSecureDetails() {
        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername(NEW_USER_NAME);

        UserEntity savedUser = sut.saveAndFlush(constructedUser);
        assertThat(constructedUser.getId(), is(notNullValue()));
        assertThat(savedUser.getId(), is(constructedUser.getId()));
        assertThat(savedUser, is(constructedUser));

        UserEntity loadedUser = sut.findById(savedUser.getId());
        assertThat(constructedUser.getId(), is(loadedUser.getId()));
        assertThat(loadedUser.getUsername(), is(NEW_USER_NAME));

        UserSecureDetailsEntity secureDetails = loadedUser.getSecureDetails();
        assertThat(secureDetails, is(nullValue()));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowExceptionIfSecureDetailsHasNotUser() {
        UserEntity constructedUser = constructUserEntity();
        constructedUser.getSecureDetails().setUser(null);

        sut.saveAndFlush(constructedUser);
    }

    @Test
    public void shouldCreateNewUserWithUserSecureDetails() {
        UserEntity constructedUser = constructUserEntity();
        constructedUser.setUsername("Another user name");

        UserEntity savedUser = sut.saveAndFlush(constructedUser);
        assertThat(constructedUser.getId(), is(notNullValue()));
        assertThat(savedUser.getId(), is(constructedUser.getId()));
        assertThat(savedUser, is(constructedUser));

        UserEntity loadedUser = sut.findById(savedUser.getId());
        assertThat(constructedUser.getId(), is(loadedUser.getId()));
        assertThat(loadedUser.getUsername(), is("Another user name"));
        assertThat(loadedUser, not(is(constructedUser)));
        assertThat(loadedUser, not(is(savedUser)));

        UserSecureDetailsEntity secureDetails = loadedUser.getSecureDetails();
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getId(), is(notNullValue()));
        assertThat(secureDetails.getLogin(), is(NEW_USER_LOGIN));
        assertThat(secureDetails.getPassword(), is(NEW_USER_PASSWORD));
        assertThat(secureDetails.getRole(), is(NEW_USER_ROLE));
        assertThat(secureDetails.getUser(), not(is(constructedUser)));
        assertThat(secureDetails.getUser(), not(is(savedUser)));
        assertThat(secureDetails.getUser(), is(loadedUser));
    }

    private UserEntity constructUserEntity() {
        UserSecureDetailsEntity constructedSecureDetails = new UserSecureDetailsEntity();
        constructedSecureDetails.setLogin(NEW_USER_LOGIN);
        constructedSecureDetails.setPassword(NEW_USER_PASSWORD);
        constructedSecureDetails.setRole(NEW_USER_ROLE);

        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername(NEW_USER_NAME);
        constructedUser.setSecureDetails(constructedSecureDetails);

        constructedSecureDetails.setUser(constructedUser);
        return constructedUser;
    }

    private UserEntity selectAndCheckUser(final TestUser testUser, final UserRole userRole) {
        UserEntity userEntity = sut.findById(testUser.getId());
        assertThat(userEntity, is(notNullValue()));
        assertThat(userEntity.getId(), is(testUser.getId()));
        assertThat(userEntity.getUsername(), is(testUser.getUserName()));

        UserSecureDetailsEntity secureDetails = userEntity.getSecureDetails();
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getUser(), is(notNullValue()));
        assertThat(secureDetails.getUser().getId(), is(testUser.getId()));
        assertThat(secureDetails.getRole(), is(userRole));

        return userEntity;
    }
}