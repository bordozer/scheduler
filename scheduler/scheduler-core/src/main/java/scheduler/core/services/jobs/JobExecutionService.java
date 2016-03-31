package scheduler.core.services.jobs;

public interface JobExecutionService {

    void execute(Long remoteJobId);
}
