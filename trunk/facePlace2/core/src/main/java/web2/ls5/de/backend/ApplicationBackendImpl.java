package web2.ls5.de.backend;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import web2.ls5.de.biz.controller.SearchEngineController;
import web2.ls5.de.biz.controller.UserStatusController;
import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.User;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;
import web2.ls5.de.persistence.Persistence;


@Named("applicationBackend")
@RequestScoped
public class ApplicationBackendImpl implements ApplicationBackend 
{

	@Inject
	@Persistence
	EntityManager em;
	
	private UserManager um;
	
	UserStatusController us = new UserStatusController();
	
	SearchEngineController se = new SearchEngineController();
	
	/* 
	 * -----------------------------------
	 * All about user: 				--
	 * -----------------------------------
	 */
	
	public void endFriendship(User shooter, User shooted)
	{
		shooter.endFrindship(shooted);
	}
	
	public User login(String username, String passwort)
	{
		return us.login(username, passwort);
	}
	
	public Vector<User> searchNames(String prefix)
	{
		return se.searchNames(prefix);
	}
	
	public void invite(User inviter, User invitee)
	{
		inviter.invite(invitee);
	}
	
	public Vector<Invitation> chooseInivitation(User invitee)
	{
		return invitee.getInvitationsList().getInvitations();
	}
	
	public void startFriendship(Invitation invitation)
	{
		invitation.approve();
	}
	
	public User register(String username, String passwort, String name)
	{
		return us.register(username, passwort, name);
	}
	

	public DBPerson createNewPerson() 
	{
		DBPerson newperson = new DBPerson();

		em.persist(newperson);

		return newperson;
	}


	public Set<DBPerson> getAllPersons() 
	{
		Query q = em.createQuery("SELECT p FROM DBPerson p", DBPerson.class);
		return new HashSet<DBPerson>(q.getResultList());
	}

	public DBPerson getPerson(long id) 
	{
		return em.find(DBPerson.class, id);
	}


	public void removePerson(long id) 
	{
		DBPerson p = getPerson(id);
		if(p != null) {
			em.remove(p);
		}
	}
	
	/* 
	 * ---------------------------
	 * All about posts:			--
	 * ---------------------------
	 */
	 
	public void post(User poster, String text)
	{
		poster.postOnAllWalls(text);
	}
	
	public Vector<Posting> getPostsWall(User user)
	{
		return user.getPostingsList().getPostingsList();
	}

	public Set<DBPost> getAllPosts() 
	{
		Query q = em.createQuery("SELECT p FROM DBPost p", DBPost.class);
		return new HashSet<DBPost>(q.getResultList());
	}
	

	public Set<DBPost> getAllPostsFromPerson(long id) 
	{
		Query q = em.createQuery("SELECT p FROM DBPost p WHERE p.creator = "+ id, DBPost.class);
		return new HashSet<DBPost>(q.getResultList());
	}
	
	/**
	 * Creates new post, stores in DB and returns it.
	 * @return New created post.
	 */

	public DBPost createNewPost()
	{
		DBPost newPost = new DBPost();
		em.persist(newPost);
		return newPost;
	}
	
	/**
	 * Removes post by given id.
	 */
	public void removePost(long id)
	{
		DBPost p = getPost(id);
		if(p != null)
		{
			em.remove(p);
		}
	}
	
	/**
	 * Returns post by given id.
	 */

	public DBPost getPost(long id) 
	{
		return em.find(DBPost.class, id);
	}
	
	
	/* 
	 * ---------------------------
	 * Something other:			--
	 * ---------------------------
	 */
	

	public String sayHello() 
	{
		return "Hello!";
	}

	public String testAll()
	{
		return "huhu";
		//return gbi.testAll();
	}
	
	/**
	 * Absolut ueberfluessig!
	 */
	public UserManager getUserManager()
	{
		if(um != null) return um;
		else 
		{
			um = new UserManager(em);
			return um;
		}
	}

	public void createTestEntries()
	{
		for(int i=0; i<15;i++)
		{
			DBPerson newPerson = createNewPerson();
			newPerson.setName("Mr.X");
			newPerson.setBirthdate(new Date((int)Math.random()*10, (int)Math.random()*10, (int)Math.random()*10));
			newPerson.setPassword(getUserManager().getHashedPassword("123"));
			
			DBPost newPost = new DBPost();
			Date bla = new Date((int)Math.random()*10, (int)Math.random()*10, (int)Math.random()*10); 
			newPost.setCreationDate(bla);
			newPost.setMsg(generateRandomString());	
			newPost.setCreator(newPerson.getId());
			
			em.persist(newPost);
		}
	}
	
	private String generateRandomString()
	{
		return java.util.UUID.randomUUID().toString();
	}  
}
