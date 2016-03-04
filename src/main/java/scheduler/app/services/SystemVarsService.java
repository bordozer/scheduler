package scheduler.app.services;

public interface SystemVarsService {

    String getProjectName();

    String getDatabaseHost();

    String getDatabasePort();

    String getDatabaseName();

    String getDatabaseUserName();

    String getDatabaseUserPassword();

    String getRootUserName();

    String getRootUserPassword();
}
