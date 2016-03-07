package scheduler.app.converters.dto;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.testng.annotations.BeforeSuite;
import scheduler.app.dto.UserDto;
import scheduler.app.models.User;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataDto;
import scheduler.app.utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDtoConverterTest {

    private static final User CURRENT_USER = new User();
    private static final long CURRENT_USER_ID = 111L;
    private static final String CURRENT_USER_NAME = "Current User";

    @InjectMocks
    private UserDtoConverter sut = new UserDtoConverter();

    @BeforeSuite
    public void init() {
        CURRENT_USER.setId(CURRENT_USER_ID);
        CURRENT_USER.setUsername(CURRENT_USER_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfCurrentUserIsNull() {
        sut.toModel(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfDtoIsNull() {
        sut.toModel(CURRENT_USER, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfModelIsNull() {
        sut.toDto(null);
    }

    @Test
    public void shouldConvertToModel() {
        User model = sut.toModel(CURRENT_USER, TestDataDto.user());

        assertEquals(TestData.USER_ID, model.getId());
        assertEquals(TestData.USER_NAME, model.getUsername());
    }

    @Test
    public void shouldConvertToDto() {
        UserDto dto = sut.toDto(TestDataModels.user());

        assertEquals(TestData.USER_ID, dto.getUserId());
        assertEquals(TestData.USER_NAME, dto.getUserName());
    }

    @Test
    public void shouldConvertToDtosIfNullIsPassed() {
        List<UserDto> dtos = sut.toDtos(null);

        assertNotNull(dtos);
        assertTrue(dtos.size() == 0);
    }

    @Test
    public void shouldConvertToDtosIfEmptyListIsPassed() {
        List<UserDto> dtos = sut.toDtos(Lists.newArrayList());

        assertNotNull(dtos);
        assertTrue(dtos.size() == 0);
    }

    @Test
    public void shouldConvertToDtos() {
        List<UserDto> models = sut.toDtos(Lists.newArrayList(TestDataModels.user()));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        UserDto dto = models.get(0);
        assertEquals(TestData.USER_ID, dto.getUserId());
        assertEquals(TestData.USER_NAME, dto.getUserName());
    }
}