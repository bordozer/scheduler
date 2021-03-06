package converters;

import com.beust.jcommander.internal.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import schemway.core.models.RemoteJob;
import schemway.web.converters.RemoteJobDtoConverter;
import schemway.web.dto.RemoteJobDto;
import utils.TestData;
import utils.TestDataDto;
import utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RemoteJobDtoConverterTest {

    @InjectMocks
    private RemoteJobDtoConverter sut = new RemoteJobDtoConverter();

    @Test
    public void shouldConvertToModel() {
        RemoteJob model = sut.toModel(TestDataModels.currentUser(), TestDataDto.remoteJob());

        Assert.assertEquals(TestData.REMOTE_JOB_ID, model.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, model.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, model.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, model.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, model.getPostJson());
    }

    @Test
    public void shouldConvertToDto() {
        RemoteJobDto dto = sut.toDto(TestDataModels.remoteJob());
        checkDto(dto);
    }

    @Test
    public void shouldConvertToDtos() {
        List<RemoteJobDto> models = sut.toDtos(Lists.newArrayList(TestDataModels.remoteJob()));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        RemoteJobDto dto = models.get(0);
        checkDto(dto);
    }

    private void checkDto(final RemoteJobDto dto) {
        assertEquals(TestData.REMOTE_JOB_ID, dto.getRemoteJobId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, dto.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, dto.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, dto.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, dto.getPostJson());
    }
}