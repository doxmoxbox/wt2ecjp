package web2.ls5.de.biz.controller;

import web2.ls5.de.biz.entities.atoms.User;
import web2.ls5.de.boundries.DbBoundry;
import web2.ls5.de.boundries.dummys.DbBoundryDummy;

public class UserStatusController {

	DbBoundry dbb = new DbBoundryDummy();
	
	
	public UserStatusController() {
		// TODO Auto-generated constructor stub
	}
	
	public User register (String username, String passwort, String name){		
		if(dbb.getUserByUsername(username) != null){
			return null;
		}
		return new User(username, passwort, name);				
	}
	
	public User login(String username, String passwort){
		User dbUser = dbb.getUserByUsername(username);
		if(dbUser.getPasswort().equals(passwort)){
			return dbUser;
		}				
		return null;
	}
}
