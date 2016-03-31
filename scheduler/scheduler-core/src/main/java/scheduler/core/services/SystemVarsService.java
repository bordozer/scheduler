package scheduler.core.services;

public interface SystemVarsService {

	String getProjectName();

	String getDbUrl();

	String getDbUser();

	String getDbPassword();

	boolean showDbLog();

	boolean schedulerAutorun();

	int schedulerStartupDelay();
}
