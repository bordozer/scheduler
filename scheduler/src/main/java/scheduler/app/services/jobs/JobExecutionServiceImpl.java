package scheduler.app.services.jobs;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import scheduler.app.models.RemoteJob;
import scheduler.app.services.remote.HttpParameters;
import scheduler.app.services.remote.WebClientService;
import scheduler.app.services.tasks.RemoteJobService;

import javax.inject.Inject;

@Service
public class JobExecutionServiceImpl implements JobExecutionService {

    private static final Logger LOGGER = Logger.getLogger(JobExecutionServiceImpl.class);

    @Inject
    private RemoteJobService remoteJobService;

    @Inject
    private WebClientService webClientService;

    @Override
    public void execute(final Long remoteJobId) {
        RemoteJob remoteJob = remoteJobService.findById(remoteJobId);
        HttpParameters parameters = new HttpParameters(remoteJob.getRequestUrl(), remoteJob.getRequestMethod())
                .withPostJson(remoteJob.getPostJson());

        try {
            webClientService.send(parameters);
            LOGGER.info(String.format("Remote job %s: successfully executed", remoteJob));
        } catch (Exception e) {
            LOGGER.error(String.format("Remote job %s: failed", remoteJob), e);
        }
    }
}
