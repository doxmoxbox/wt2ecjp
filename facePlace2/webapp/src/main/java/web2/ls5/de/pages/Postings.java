package web2.ls5.de.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

public class Postings
{
	@Inject
	ApplicationBackend backend;
	
	/*@Property
	private DBPost post;
	
	public Set<DBPost> getAllPosts()
	{
		return backend.getAllPosts();
	}*/
	public String getHello()
	{
		return backend.sayHello();
	}
}