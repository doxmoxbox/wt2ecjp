package web2.ls5.de.biz.controller;

import web2.ls5.de.biz.entities.atoms.UserPerson;
import web2.ls5.de.boundries.DbBoundry;
import web2.ls5.de.boundries.dummys.DbBoundryDummy;
import web2.ls5.de.boundries.impls.DbBoundryImpl;

public class UserStatusController {

	DbBoundryImpl dbb = new DbBoundryImpl();
	
	public UserStatusController() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPerson register (String username, String passwort, String name){		
		if(dbb.getUserByUsername(username) != null){
			return null;
		}
		return new UserPerson(username, passwort, name);				
	}
	
	public UserPerson login(String username, String passwort){
		UserPerson dbUser = dbb.getUserByUsername(username);
		if(dbUser.getPasswort().equals(passwort)){
			return dbUser;
		}				
		return null;
	}
}
