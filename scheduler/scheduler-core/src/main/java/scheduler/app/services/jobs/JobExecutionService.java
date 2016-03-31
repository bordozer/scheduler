package scheduler.app.services.jobs;

public interface JobExecutionService {

    void execute(Long remoteJobId);
}
