package web2.ls5.de.boundries;

import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;

@Named
public interface GuiBoundry {

	// User status
	
	
	public boolean register(String username, String passwort, String name);
/*	public boolean login(Session session, String username, String passwort);
	public boolean logout(Session session, User user);
	
	public boolean closeBrowserSession(Session session);*/
	
	//Search - invie - ignore or accept(= startFriendship) - endFriendship
	
	public Vector<UserPerson> searchNames(String prefix);	
	
	public void invite(UserPerson inviter, UserPerson invitee);	
	public Vector<Invitation> chooseInivitation(UserPerson invitee);	
	public void acceptInvitation(Invitation invitation);
	public void ignoreInvitation(Invitation invitation);
	
	public void endFriendship(UserPerson shooter, UserPerson shooted);
	
	// Posts
	
	public void post(UserPerson poster, String text);
	public Vector<Posting> getPostsWall(UserPerson user);

}
