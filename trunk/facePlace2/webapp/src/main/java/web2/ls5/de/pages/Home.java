package web2.ls5.de.pages;

import java.util.Set;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import web2.ls5.de.backend.ApplicationBackend;
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
	private AlertManager alertManager;

	@Property
	private DBPost post;
	
	//@Component
	//private PostingComponent postsComponent;
	
	@Inject
	private ApplicationBackend backend;
	
	public Set<DBPost> getPosts()
	{
		return backend.getAllPosts();
	}
	
	public void onActionFromTestMsg()
    {
    	alertManager.info("Muhaha Test");
    }
	
	
	
}
