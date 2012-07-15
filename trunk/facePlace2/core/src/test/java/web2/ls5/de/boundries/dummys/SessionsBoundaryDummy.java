package web2.ls5.de.boundries.dummys;

import java.util.Vector;



import web2.ls5.de.boundries.SessionsBoundary;
import web2.ls5.de.entities.atoms.Session;
import web2.ls5.de.entities.atoms.User;

public class SessionsBoundaryDummy implements SessionsBoundary {

	Vector<Session> sessions;
	
	public SessionsBoundaryDummy() {
		// TODO Auto-generated constructor stub
		sessions = new Vector<Session>();
	}
	
	public Session openNewBrowserSession(){
		return new Session();
	}
	
	public boolean linkUserWithSession(Session session, User user){
		if(session.getUser() == null || user.getSession() == null){
			session.setUser(user);
			user.setSession(session);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUserInSession(User user){
		return user.getSession() != null;
	}
	
	public Session getSessionFromUser(User user){
		return user.getSession();
	}
	
	public boolean removeUserFromSession(Session session){
		if(session.getUser() == null){
			return false;
		} else {
			session.setUser(null);
			return true;
		}
	}

	public boolean closeBrowserSession(Session session){
		if(sessions.contains(session)){
			sessions.remove(session);
			return true;
		} else {
			return false;
		}
	}
}