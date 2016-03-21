package scheduler.app.repositories;

import org.junit.Test;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserRole;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserEntityTest extends AbstractRepositoryTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void shouldSaveUserAndUserDetailsWhenUserIsSaved() {
        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername("User name");

        UserSecureDetailsEntity constructedUserDetails = new UserSecureDetailsEntity();
        constructedUserDetails.setLogin("user_login");
        constructedUserDetails.setRole(UserRole.USER);
        constructedUserDetails.setPassword("11111111111111111111111111111111111111111111");
        constructedUserDetails.setUser(constructedUser);

        constructedUser.setSecureDetails(constructedUserDetails);

        UserEntity savedUserEntity = userRepository.saveAndFlush(constructedUser);

        UserEntity loadedUserEntity = userRepository.findById(savedUserEntity.getId());
        assertThat(loadedUserEntity, not(is(savedUserEntity)));
        assertThat(loadedUserEntity.getId(), is(savedUserEntity.getId()));

        UserSecureDetailsEntity loadedUserSecureDetailsEntity = loadedUserEntity.getSecureDetails();
        assertThat(loadedUserSecureDetailsEntity, not(is(savedUserEntity.getSecureDetails())));
        assertThat(loadedUserSecureDetailsEntity.getUser(), not(is(constructedUser)));
        assertThat(loadedUserSecureDetailsEntity.getId(), is(loadedUserSecureDetailsEntity.getId()));
        assertThat(loadedUserSecureDetailsEntity.getUser().getId(), is(loadedUserSecureDetailsEntity.getUser().getId()));
    }
}
