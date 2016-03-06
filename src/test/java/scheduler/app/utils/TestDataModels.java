package scheduler.app.utils;

import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.app.models.RemoteJob;

public class TestDataModels {

	public static final Long REMOTE_JOB_ID = 111L;
	public static final String REQUEST_URL = "nba.com";
	public static final RequestMethod REQUEST_METHOD = RequestMethod.POST;
	public static final String AUTH_STRING = "ABCDE";
	public static final String POST_JSON = "{value:111}";

	public static RemoteJob remoteJob() {
		final RemoteJob model = new RemoteJob();
		model.setId(REMOTE_JOB_ID);
		model.setRequestUrl(REQUEST_URL);
		model.setRequestMethod(REQUEST_METHOD);
		model.setAuthString(AUTH_STRING);
		model.setPostJson(POST_JSON);
		return model;
	}
}
