package scheduler.app.services;

public interface SystemVarsService {

	String getProjectName();

	String getDbUrl();

	String getDbUser();

	String getDbPassword();

	boolean showDbLog();
}
