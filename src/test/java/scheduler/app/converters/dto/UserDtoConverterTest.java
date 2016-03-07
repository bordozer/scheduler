package scheduler.app.converters.dto;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private UserDtoConverter sut = new UserDtoConverter();


    @Test
    public void shouldConvertToModel() {
        User model = sut.toModel(TestDataModels.currentUser(), TestDataDto.user());

        assertEquals(TestData.USER_ID, model.getId());
        assertEquals(TestData.USER_NAME, model.getUsername());
    }

    @Test
    public void shouldConvertToDto() {
        UserDto dto = sut.toDto(TestDataModels.user());
        checkDto(dto);
    }

    @Test
    public void shouldConvertToDtos() {
        List<UserDto> models = sut.toDtos(Lists.newArrayList(TestDataModels.user()));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        UserDto dto = models.get(0);
        checkDto(dto);
    }

    private void checkDto(final UserDto dto) {
        assertEquals(TestData.USER_ID, dto.getUserId());
        assertEquals(TestData.USER_NAME, dto.getUserName());
    }
}