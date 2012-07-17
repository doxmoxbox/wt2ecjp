package web2.ls5.de.biz.controller;

import java.util.Collections;
import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.UserPerson;

@Named
public class SearchEngineController {

	public SearchEngineController() {
		// TODO Auto-generated constructor stub
	}
	public Vector<UserPerson> searchNames(String prefix){
		Vector<UserPerson> match = new Vector<UserPerson>();
		for(UserPerson u : match){
			if(u.getName().startsWith(prefix)){
				match.add(u);
			}
		}
		
		//Collections.sort(match);
		
		return match;
	}
}
