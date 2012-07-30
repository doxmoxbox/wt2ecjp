package web2.ls5.de.pages;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.ioc.annotations.*;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.biz.entities.atoms.Invitation;
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
	
	@Property
	private DBPerson person;
	
	/*@InjectComponent("postGrid")
	private Grid postGrid;*/
	
	//@Component
	//private PostingComponent postsComponent;
	
	@Inject
	private ApplicationBackend backend;
	
	public Vector<DBPost> getPosts()
	{
		//return backend.getAllPosts();
		Vector<DBPost> posts = new Vector<DBPost>(backend.getAllPostingsFromDBPerson(loggedInPerson));
		Collections.sort(posts);
		return posts;
	}
	
	public Set<DBPerson> getFriends()
	{
		return backend.getFriends(loggedInPerson);
	}
	
	public void lookforInvitations()
	{
		Set<Invitation> invs = backend.getInvitations(loggedInPerson);
		for(Invitation inv : invs)
		{
			alertManager.alert(Duration.TRANSIENT, Severity.INFO, "faceFind: Neuer Freund!");
		}
		
	}
	
	void onActionFromDelete(long userId)
    {
        backend.removeFriend(loggedInPerson, backend.getPerson(userId));
        alertManager.alert(Duration.TRANSIENT, Severity.INFO, "faceFind: Freund entfernt!");
    }  
	
	Object onActivation()
	{
		if (loggedInPerson == null)return "index";
		return null;
	}
	
	void pageLoaded()
	{
		lookforInvitations();
	}
	
	public String getFormattedPostDate()
	{
		 SimpleDateFormat formatter = new SimpleDateFormat("d.M.y - H:m:s");//"d.m.y - H:m:s"
		 return formatter.format(post.getCreationDate());
	  
	}
	
	public String getLoggedInPersonBirthdate()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("d.M.y");//"d.m.y - H:m:s"
		 return formatter.format(loggedInPerson.getBirthdate());
	}
	
}
