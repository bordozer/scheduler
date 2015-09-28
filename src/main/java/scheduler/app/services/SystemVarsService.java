package scheduler.app.services;

import java.util.List;

public interface SystemVarsService {

	String getProjectName();

	String getDatabaseHost();

	String getDatabasePort();

	String getDatabaseName();

	String getDatabaseUserName();

	String getDatabaseUserPassword();

	List<Integer> getAdminIds();
}
