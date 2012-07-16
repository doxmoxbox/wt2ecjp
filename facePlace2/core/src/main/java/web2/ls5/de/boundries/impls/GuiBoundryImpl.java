package web2.ls5.de.boundries.impls;

import java.util.Vector;
import javax.inject.Named;


import web2.ls5.de.biz.controller.SearchEngineController;
import web2.ls5.de.biz.controller.UserStatusController;
import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.User;
import web2.ls5.de.boundries.tests.GuiBoundryTest;

@Named
public class GuiBoundryImpl {

	UserStatusController us = new UserStatusController();
	SearchEngineController se = new SearchEngineController();
	GuiBoundryTest gbt = new GuiBoundryTest();
	
	public GuiBoundryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String testAll(){
		return gbt.testAll();
	}
	
	public User register(String username, String passwort, String name){
		return us.register(username, passwort, name);
	}

	public User login(String username, String passwort){
		return us.login(username, passwort);
	}
	
	public Vector<User> searchNames(String prefix){
		return se.searchNames(prefix);
	}
	
	public void invite(User inviter, User invitee){
		inviter.invite(invitee);
	}
	
	public Vector<Invitation> chooseInivitation(User invitee){
		return invitee.getInvitationsList().getInvitations();
	}
	
	public void startFriendship(Invitation invitation){
		invitation.approve();
	}
	
	public void endFriendship(User shooter, User shooted){
		shooter.endFrindship(shooted);
	}
	
	public void post(User poster, String text){
		poster.postOnAllWalls(text);
	}
	
	public Vector<Posting> getPostsWall(User user){
		return user.getPostingsList().getPostingsList();
	}
}