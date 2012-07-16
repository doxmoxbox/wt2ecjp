package web2.ls5.de.boundries;

import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.Session;
import web2.ls5.de.biz.entities.atoms.User;

@Named
public interface GuiBoundry {

	// User status
	
	public Session openNewBrowserSession();
	
	public boolean register(String username, String passwort, String name);
	public boolean login(Session session, String username, String passwort);
	public boolean logout(Session session, User user);
	
	public boolean closeBrowserSession(Session session);
	
	//Search - invie - ignore or accept(= startFriendship) - endFriendship
	
	public Vector<User> searchNames(String prefix);	
	
	public void invite(User inviter, User invitee);	
	public Vector<Invitation> chooseInivitation(User invitee);	
	public void acceptInvitation(Invitation invitation);
	public void ignoreInvitation(Invitation invitation);
	
	public void endFriendship(User shooter, User shooted);
	
	// Posts
	
	public void post(User poster, String text);
	public Vector<Posting> getPostsWall(User user);

}
