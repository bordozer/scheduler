package scheduler.app.entities;

import org.junit.Test;
import scheduler.app.AbstractIntegrationTest;
import scheduler.core.entities.UserEntity;
import scheduler.core.entities.UserSecureDetailsEntity;
import scheduler.core.models.UserRole;
import scheduler.core.repositories.UserRepository;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserEntityTest extends AbstractIntegrationTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void shouldSaveUserIfSecurityDetailsAreNull() {
        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername("User name");

        UserEntity createdUserEntity = userRepository.saveAndFlush(constructedUser);

        UserEntity loadedUserEntity = userRepository.findById(createdUserEntity.getId());
        assertThat(loadedUserEntity, not(is(createdUserEntity)));
        assertThat(loadedUserEntity.getId(), is(createdUserEntity.getId()));

        UserSecureDetailsEntity loadedUserSecureDetailsEntity = loadedUserEntity.getSecureDetails();
        assertThat(loadedUserSecureDetailsEntity, is(nullValue()));
    }

    @Test
    public void shouldCreateUserAndUpdateThen() {
        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername("User name 1");
        UserSecureDetailsEntity constructedUserDetails = new UserSecureDetailsEntity();
        constructedUserDetails.setLogin("user_login");
        constructedUserDetails.setRole(UserRole.USER);
        constructedUserDetails.setPassword("11111111111111111111111111111111111111111111");
        constructedUserDetails.setUser(constructedUser);
        constructedUser.setSecureDetails(constructedUserDetails);

        UserEntity createdUserEntity = userRepository.saveAndFlush(constructedUser);

        UserEntity loadedUserEntity = userRepository.findById(createdUserEntity.getId());
        loadedUserEntity.setUsername("New User Name");
        UserEntity updatedUserEntity = userRepository.saveAndFlush(loadedUserEntity);
        assertThat(updatedUserEntity, is(notNullValue()));
        assertThat(updatedUserEntity.getId(), is(createdUserEntity.getId()));
        assertThat(updatedUserEntity.getUsername(), is("New User Name"));

        UserSecureDetailsEntity secureDetailsOfUpdatedUser = updatedUserEntity.getSecureDetails();
        assertThat(secureDetailsOfUpdatedUser, not(is(nullValue())));
        assertThat(secureDetailsOfUpdatedUser.getId(), is(createdUserEntity.getSecureDetails().getId()));
    }

    @Test
    public void shouldSaveUserAndUserDetailsWhenUserIsSaved() {
        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername("User name 2");

        UserSecureDetailsEntity constructedUserDetails = new UserSecureDetailsEntity();
        constructedUserDetails.setLogin("user_login_1");
        constructedUserDetails.setRole(UserRole.USER);
        constructedUserDetails.setPassword("11111111111111111111111111111111111111111111");
        constructedUserDetails.setUser(constructedUser);

        constructedUser.setSecureDetails(constructedUserDetails);

        UserEntity createdUserEntity = userRepository.saveAndFlush(constructedUser);

        UserEntity loadedUserEntity = userRepository.findById(createdUserEntity.getId());
        assertThat(loadedUserEntity, not(is(createdUserEntity)));
        assertThat(loadedUserEntity.getId(), is(createdUserEntity.getId()));

        UserSecureDetailsEntity loadedUserSecureDetailsEntity = loadedUserEntity.getSecureDetails();
        assertThat(loadedUserSecureDetailsEntity, not(is(createdUserEntity.getSecureDetails())));
        assertThat(loadedUserSecureDetailsEntity.getUser(), not(is(constructedUser)));
        assertThat(loadedUserSecureDetailsEntity.getId(), is(loadedUserSecureDetailsEntity.getId()));
        assertThat(loadedUserSecureDetailsEntity.getUser().getId(), is(loadedUserSecureDetailsEntity.getUser().getId()));
    }
}
