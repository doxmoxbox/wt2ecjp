package web2.ls5.de.backend;

import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 09.05.12
 * Time: 08:11
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationBackend 
{
	/* All about people: */
	public DBPerson createNewPerson();
	public Set<DBPerson> getAllPersons();
	public DBPerson getPerson(long id);
	public void removePerson(long id);
	
	/* All about posts: */
	public Set<DBPost> getAllPosts();
	public DBPost createNewPost();
	public void removePost(long id);
	public DBPost getPost(long id);
	
	
	/* Something other */ 
	public void createTestEntries();
	public String sayHello();
}