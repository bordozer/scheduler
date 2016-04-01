package schemway.core.converters;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import schemway.core.entities.RemoteJobEntity;
import schemway.core.models.RemoteJob;
import schemway.core.utils.TestData;
import schemway.core.utils.TestDataEntities;
import schemway.core.utils.TestDataModels;

import static org.junit.Assert.assertEquals;

public class RemoteJobEntityConverterImplTest {

    @InjectMocks
    private RemoteJobEntityConverterImpl sut = new RemoteJobEntityConverterImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfEntityIsNullWhenPopulatesEntity() {
        sut.populateEntity(null, new RemoteJob());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserIsNullWhenPopulatesEntity() {
        sut.populateEntity(new RemoteJobEntity(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserIsNullWhenConvertsToModel() {
        sut.toModel(null);
    }

    @Test
    public void shouldPopulateEntity() {
        final RemoteJobEntity entity = new RemoteJobEntity();
        sut.populateEntity(entity, TestDataModels.remoteJob());

        Assert.assertEquals(TestData.REMOTE_JOB_ID, entity.getId());
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