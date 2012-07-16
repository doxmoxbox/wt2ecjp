package web2.ls5.de.backend;

import java.util.Set;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 09.05.12
 * Time: 08:11
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationBackend 
{
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
