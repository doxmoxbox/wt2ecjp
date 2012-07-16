package web2.ls5.de.biz.controller;

import java.util.Collections;
import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.User;

@Named
public class SearchEngineController {

	public SearchEngineController() {
		// TODO Auto-generated constructor stub
	}
	public Vector<User> searchNames(String prefix){
		Vector<User> match = new Vector<User>();
		for(User u : match){
			if(u.getName().startsWith(prefix)){
				match.add(u);
			}
		}
		
		Collections.sort(match);
		
		return match;
	}
}
