package web2.ls5.de.boundries;

import web2.ls5.de.entities.atoms.Session;
import web2.ls5.de.entities.atoms.User;

public interface SessionsBoundary {
	
	public Session openNewBrowserSession();

	public boolean linkUserWithSession(Session session, User user);
	public boolean isUserInSession(User user);
	public Session getSessionFromUser(User user);
	public boolean removeUserFromSession(Session session);
	
	public boolean closeBrowserSession(Session session);

}
