package schemway.scheduler.jobs;

public interface JobExecutionService {

    void execute(Long remoteJobId);
}
