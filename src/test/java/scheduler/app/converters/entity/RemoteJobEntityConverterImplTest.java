package scheduler.app.converters.entity;

import org.junit.Test;
import org.mockito.InjectMocks;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.models.RemoteJob;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataEntities;
import scheduler.app.utils.TestDataModels;

import static org.junit.Assert.assertEquals;

public class RemoteJobEntityConverterImplTest {

    @InjectMocks
    private RemoteJobEntityConverterImpl sut = new RemoteJobEntityConverterImpl();

    @Test
    public void shouldPopulateEntity() {
        final RemoteJobEntity entity = new RemoteJobEntity();
        sut.populateEntity(entity, TestDataModels.remoteJob());

        assertEquals(TestData.REMOTE_JOB_ID, entity.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, entity.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, entity.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, entity.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, entity.getPostJson());
    }

    @Test
    public void shouldConvertEntityToModel() {
        final RemoteJob model = sut.toModel(TestDataEntities.remoteJob());

        assertEquals(TestData.REMOTE_JOB_ID, model.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, model.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, model.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, model.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, model.getPostJson());
    }
}