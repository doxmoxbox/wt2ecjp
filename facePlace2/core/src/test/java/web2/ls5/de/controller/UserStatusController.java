package web2.ls5.de.controller;

import web2.ls5.de.boundries.DbBoundry;
import web2.ls5.de.boundries.SessionsBoundary;
import web2.ls5.de.boundries.dummys.DbBoundryDummy;
import web2.ls5.de.boundries.dummys.SessionsBoundaryDummy;
import web2.ls5.de.entities.atoms.Session;
import web2.ls5.de.entities.atoms.User;

public class UserStatusController {

	DbBoundry dbb = new DbBoundryDummy();
	SessionsBoundary sb = new SessionsBoundaryDummy();
	
	
	public UserStatusController() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean register (String username, String passwort, String name){		
		if(dbb.getUserByUsername(username) != null){
			return false;
		}
		new User(username, passwort, name);				
		return true;
	}
	
	public boolean login(Session session, String username, String passwort){
		User dbUser = dbb.getUserByUsername(username);
		if(sb.isUserInSession(dbUser)){
			return false;
		} else {
			if(dbUser.getPasswort().equals(passwort)){
				sb.linkUserWithSession(session, dbUser);			
				return true;
			}				
			return false;
		}
	}
	
	public boolean logout(Session session, User user){
		if(sb.isUserInSession(user)){
			sb.removeUserFromSession(session);
			return true;
		} else {
			return false;
		}
	}
}
