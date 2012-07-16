package web2.ls5.de.pages;

import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.components.Layout;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;

/**
 * Page for Userhome. Contains other components.
 * 
 */
public class Home 
{
	@Property
	@SessionState(create=false)
	private DBPerson loggedInPerson;
	
	@Inject
	private ApplicationBackend backend;
	
	@Property
	private DBPost post;
	
	public Set<DBPost> getPosts()
	{
		return backend.getAllPosts();
	}
	
	
	
}
