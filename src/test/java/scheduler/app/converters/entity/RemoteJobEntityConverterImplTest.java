package scheduler.app.converters.entity;

import org.junit.Test;
import org.mockito.InjectMocks;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.models.RemoteJob;
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

        assertEquals(TestDataModels.REMOTE_JOB_ID, entity.getId());
        assertEquals(TestDataModels.REQUEST_URL, entity.getRequestUrl());
        assertEquals(TestDataModels.REQUEST_METHOD, entity.getRequestMethod());
        assertEquals(TestDataModels.AUTH_STRING, entity.getAuthString());
        assertEquals(TestDataModels.POST_JSON, entity.getPostJson());
    }

    @Test
    public void shouldConvertEntityToModel() {
        final RemoteJob model = sut.toModel(TestDataEntities.remoteJob());

        assertEquals(TestDataModels.REMOTE_JOB_ID, model.getId());
        assertEquals(TestDataModels.REQUEST_URL, model.getRequestUrl());
        assertEquals(TestDataModels.REQUEST_METHOD, model.getRequestMethod());
        assertEquals(TestDataModels.AUTH_STRING, model.getAuthString());
        assertEquals(TestDataModels.POST_JSON, model.getPostJson());
    }
}