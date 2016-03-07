package scheduler.app.converters.dto;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.testng.annotations.BeforeSuite;
import scheduler.app.dto.RemoteJobDto;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataDto;
import scheduler.app.utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RemoteJobDtoConverterTest {

    private static final User CURRENT_USER = new User();
    private static final long CURRENT_USER_ID = 111L;
    private static final String CURRENT_USER_NAME = "Current User";

    @InjectMocks
    private RemoteJobDtoConverter sut = new RemoteJobDtoConverter();

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
        RemoteJob model = sut.toModel(CURRENT_USER, TestDataDto.remoteJob());

        assertEquals(TestData.REMOTE_JOB_ID, model.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, model.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, model.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, model.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, model.getPostJson());
    }

    @Test
    public void shouldConvertToDto() {
        RemoteJobDto dto = sut.toDto(TestDataModels.remoteJob());

//        assertEquals(TestData.USER_ID, dto.getUserId());
//        assertEquals(TestData.USER_NAME, dto.getUserName());
    }

    @Test
    public void shouldConvertToDtosIfNullIsPassed() {
        List<RemoteJobDto> dtos = sut.toDtos(null);

        assertNotNull(dtos);
        assertTrue(dtos.size() == 0);
    }

    @Test
    public void shouldConvertToDtosIfEmptyListIsPassed() {
        List<RemoteJobDto> dtos = sut.toDtos(Lists.newArrayList());

        assertNotNull(dtos);
        assertTrue(dtos.size() == 0);
    }

    @Test
    public void shouldConvertToDtos() {
        List<RemoteJobDto> models = sut.toDtos(Lists.newArrayList(TestDataModels.remoteJob()));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        RemoteJobDto dto = models.get(0);
//        assertEquals(TestData.USER_ID, dto.getUserId());
//        assertEquals(TestData.USER_NAME, dto.getUserName());
    }
}