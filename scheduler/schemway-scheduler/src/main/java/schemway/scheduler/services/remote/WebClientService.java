package schemway.scheduler.services.remote;

public interface WebClientService {

    void send(HttpParameters parameters) throws Exception;
}
