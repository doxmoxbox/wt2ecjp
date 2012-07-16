package web2.ls5.de.boundries.impls;

import java.util.Vector;
import javax.inject.Named;


import web2.ls5.de.biz.controller.SearchEngineController;
import web2.ls5.de.biz.controller.UserStatusController;
import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.Session;
import web2.ls5.de.biz.entities.atoms.User;
import web2.ls5.de.boundries.SessionsBoundary;
import web2.ls5.de.boundries.dummys.SessionsBoundaryDummy;

@Named
public class GuiBoundryImpl {

	SessionsBoundary sb = new SessionsBoundaryDummy();
	UserStatusController us = new UserStatusController();
	SearchEngineController se = new SearchEngineController();
	
	public GuiBoundryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public Session openNewBrowserSession(){
		return sb.openNewBrowserSession();
	}
	
	public boolean register(String username, String passwort, String name){
		return us.register(username, passwort, name);
	}

	public boolean login(Session session, String username, String passwort){
		return us.login(session, username, passwort);
	}
	
	public boolean logout(Session session, User user){
		return us.logout(session, user);
	}
	
	public boolean closeBrowserSession(Session session){
		return sb.closeBrowserSession(session);
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