package web2.ls5.de.backend;

import web2.ls5.de.boundries.impls.GuiBoundryImpl;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;
import web2.ls5.de.persistence.Persistence;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 09.05.12
 * Time: 08:12
 * To change this template use File | Settings | File Templates.
 */
@Named("applicationBackend")
@RequestScoped
public class ApplicationBackendImpl implements ApplicationBackend {

	@Inject
	@Persistence
	EntityManager em;
	
	GuiBoundryImpl gbi = new GuiBoundryImpl();
	
	public String testAll(){
		return gbi.testAll();
	}
	
	private UserManager um;
	/**
	 * Absolut ueberfluessig!
	 */
	@Override
	public UserManager getUserManager()
	{
		if(um != null) return um;
		else 
		{
			um = new UserManager(em);
			return um;
		}
	}
	/* 
	 * -----------------------------------
	 * All about people: 				--
	 * -----------------------------------
	 */
	
	@Override
	public DBPerson createNewPerson() 
	{
		DBPerson newperson = new DBPerson();

		em.persist(newperson);

		return newperson;
	}

	@Override
	public Set<DBPerson> getAllPersons() 
	{
		Query q = em.createQuery("SELECT p FROM DBPerson p", DBPerson.class);
		return new HashSet<DBPerson>(q.getResultList());
	}

	@Override
	public DBPerson getPerson(long id) 
	{
		return em.find(DBPerson.class, id);
	}

	@Override
	public void removePerson(long id) {
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
	 
	@Override
	public Set<DBPost> getAllPosts() 
	{
		Query q = em.createQuery("SELECT p FROM DBPost p", DBPost.class);
		return new HashSet<DBPost>(q.getResultList());
	}
	
	@Override
	public Set<DBPost> getAllPostsFromPerson(long id) 
	{
		Query q = em.createQuery("SELECT p FROM DBPost p WHERE p.creator = "+ id, DBPost.class);
		return new HashSet<DBPost>(q.getResultList());
	}
	
	/**
	 * Creates new post, stores in DB and returns it.
	 * @return New created post.
	 */
	@Override
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
	@Override
	public DBPost getPost(long id) 
	{
		return em.find(DBPost.class, id);
	}
	
	
	/* 
	 * ---------------------------
	 * Something other:			--
	 * ---------------------------
	 */
	
	@Override
	public String sayHello() 
	{
		return "Hello!";
	}

	@Override
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
