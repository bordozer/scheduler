package scheduler.scheduler.jobs;

public interface JobExecutionService {

    void execute(Long remoteJobId);
}
