package web2.ls5.de.boundries;

import java.util.Vector;
import javax.inject.Named;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.User;

@Named
public interface GuiBoundry {
	
	public String testAll();

	// User status
	
	public User register(String username, String passwort, String name);
	public User login(String username, String passwort);
	
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
