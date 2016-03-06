package scheduler.app.converters.entity;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.models.RemoteJob;
import scheduler.app.utils.TestDataEntityConstructor;

import static org.junit.Assert.assertEquals;

public class RemoteJobEntityConverterImplTest {

	private static final Long REMOTE_JOB_ID = 111L;
	private static final String REQUEST_URL = "nba.com";
	private static final RequestMethod REQUEST_METHOD = RequestMethod.POST;
	private static final String AUTH_STRING = "ABCDE";
	private static final String POST_JSON = "{value:111}";

	@InjectMocks
	private RemoteJobEntityConverterImpl sut = new RemoteJobEntityConverterImpl();

	@Test
	public void shouldPopulateEntity() {
		final RemoteJobEntity entity = new RemoteJobEntity();
		sut.populateEntity(entity, constructModel());

		assertEquals(REMOTE_JOB_ID, entity.getId());
		assertEquals(REQUEST_URL, entity.getRequestUrl());
		assertEquals(REQUEST_METHOD, entity.getRequestMethod());
		assertEquals(AUTH_STRING, entity.getAuthString());
		assertEquals(POST_JSON, entity.getPostJson());
	}

	@Test
	public void shouldConvertEntityToModel() {

		final RemoteJob model = sut.toModel(TestDataEntityConstructor.remoteJobEntity(TestDataEntityConstructor.schedulerTaskEntity(user)));

		assertEquals(REMOTE_JOB_ID, model.getId());
		assertEquals(REQUEST_URL, model.getRequestUrl());
		assertEquals(REQUEST_METHOD, model.getRequestMethod());
		assertEquals(AUTH_STRING, model.getAuthString());
		assertEquals(POST_JSON, model.getPostJson());
	}

	private RemoteJob constructModel() {
		final RemoteJob model = new RemoteJob();
		model.setId(REMOTE_JOB_ID);
		model.setRequestUrl(REQUEST_URL);
		model.setRequestMethod(REQUEST_METHOD);
		model.setAuthString(AUTH_STRING);
		model.setPostJson(POST_JSON);
		return model;
	}
}