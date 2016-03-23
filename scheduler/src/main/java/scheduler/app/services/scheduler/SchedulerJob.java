package scheduler.app.services.scheduler;

import org.apache.log4j.Logger;
import org.dom4j.IllegalAddException;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.RequestMethod;
import scheduler.app.services.remote.RemoteClientService;

import java.io.IOException;

public class SchedulerJob extends QuartzJobBean {

    private static final Logger LOGGER = Logger.getLogger(SchedulerJob.class);

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        RemoteJob remoteJob = (RemoteJob) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK_REMOTE_JOB);
        RemoteClientService remoteClientService = (RemoteClientService) dataMap.get(SchedulerTaskInitializationServiceImpl.SCHEDULER_TASK_HTTP_CLIENT_SERVICE);

        RequestMethod requestMethod = remoteJob.getRequestMethod();
        LOGGER.debug(String.format("REMOTE JOB: #%d %s (%s)", remoteJob.getId(), remoteJob.getRequestUrl(), requestMethod));

        switch (requestMethod) {
            case GET:
                try {
                    remoteClientService.sendGet(remoteJob.getRequestUrl());
                } catch (IOException e) {
                    LOGGER.error(String.format("Error send GET request to %s (remote gib %d)", remoteJob.getRequestUrl(), remoteJob.getId()), e);
                }
                break;
            case POST:
                try {
                    remoteClientService.sendPost(remoteJob.getRequestUrl(), remoteJob.getPostJson());
                } catch (Exception e) {
                    LOGGER.error(String.format("Error send POST request to %s (remote gib %d)", remoteJob.getRequestUrl(), remoteJob.getId()), e);
                }
                break;
            default:
                throw new IllegalAddException(String.format("Unsupported Request Method: '%s'", requestMethod));
        }
    }
}
