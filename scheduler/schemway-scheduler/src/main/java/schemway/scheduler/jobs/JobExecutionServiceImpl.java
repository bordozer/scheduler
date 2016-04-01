package schemway.scheduler.jobs;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import schemway.core.models.RemoteJob;
import schemway.scheduler.services.remote.HttpParameters;
import schemway.scheduler.services.remote.WebClientService;
import schemway.core.services.tasks.RemoteJobService;

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
