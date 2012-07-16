package web2.ls5.de.backend;

import java.util.Set;
import java.util.Vector;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.User;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;


public interface ApplicationBackend 
{
	
	/*Neu von Emil*/
	public void endFriendship(User shooter, User shooted);
	public User login(String username, String passwort);
	public Vector<User> searchNames(String prefix);
	public void invite(User inviter, User invitee);
	public Vector<Invitation> chooseInivitation(User invitee);
	public void startFriendship(Invitation invitation);
	public User register(String username, String passwort, String name);
	
	public void post(User poster, String text);
	public Vector<Posting> getPostsWall(User user);
	
	
	/* Test all */
	
	public String testAll();
	
	/* All about people: */
	public DBPerson createNewPerson();
	public Set<DBPerson> getAllPersons();
	public DBPerson getPerson(long id);
	public void removePerson(long id);
	
	/* All about posts: */
	public Set<DBPost> getAllPosts();
	public Set<DBPost> getAllPostsFromPerson(long id);
	public DBPost createNewPost();
	public void removePost(long id);
	public DBPost getPost(long id);
	
	
	/* Something other */ 
	public UserManager getUserManager();
	public void createTestEntries();
	public String sayHello();
}
