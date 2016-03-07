package scheduler.app.controllers.rest.scheduler.tasks.item.edit.converters;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.RemoteJobEditDto;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataDto;
import scheduler.app.utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RemoteJobEditDtoConverterTest {

    @InjectMocks
    private RemoteJobEditDtoConverter sut = new RemoteJobEditDtoConverter();

    @Test
    public void shouldConvertToModel() {
        User currentUser = TestDataModels.currentUser();

        RemoteJob model = sut.toModel(currentUser, TestDataDto.remoteJobEdit());

        assertEquals(TestData.REMOTE_JOB_ID, model.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, model.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, model.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, model.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, model.getPostJson());
    }

    @Test
    public void shouldConvertToDto() {
        RemoteJobEditDto dto = sut.toDto(TestDataModels.remoteJob());
        checkDto(dto);
    }

    @Test
    public void shouldConvertToDtos() {
        List<RemoteJobEditDto> models = sut.toDtos(Lists.newArrayList(TestDataModels.remoteJob()));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        RemoteJobEditDto dto = models.get(0);
        checkDto(dto);
    }

    private void checkDto(final RemoteJobEditDto dto) {
        assertEquals(TestData.REMOTE_JOB_ID, dto.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, dto.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, dto.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, dto.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, dto.getPostJson());
    }
}