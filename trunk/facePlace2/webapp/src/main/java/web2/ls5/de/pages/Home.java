package web2.ls5.de.pages;

import java.util.Set;
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
	
	@InjectComponent("postGrid")
	private Grid postGrid;
	
	//@Component
	//private PostingComponent postsComponent;
	
	@Inject
	private ApplicationBackend backend;
	
	public Set<DBPost> getPosts()
	{
		//return backend.getAllPosts();
    return backend.getAllPostingsFromDBPerson(loggedInPerson);
	}
	
	public Set<DBPerson> getFriends()
	{
		return backend.getFriends(loggedInPerson);
	}
	
	public void onActionFromTestMsg()
    {
    	alertManager.info("Muhaha Test");
    }
	
	public void onActionFromGetInvitations()
	{
		Set<Invitation> invs = backend.getInvitations(loggedInPerson);
		for(Invitation inv : invs)
		{
			alertManager.alert(Duration.UNTIL_DISMISSED, Severity.INFO, "faceFind: Neuer Freund!");
		}
		
	}
	
	void onActionFromDelete(long userId)
    {
        backend.removeFriend(loggedInPerson, backend.getPerson(userId));
        alertManager.alert(Duration.TRANSIENT, Severity.INFO, "faceFind: Freund entfernt!");
    }  
	
	Object onActivate()
	{
		if (loggedInPerson == null)return "index";
		return null;
	}
	
	void setupRender() 
	{
			if (postGrid.getSortModel().getSortConstraints().isEmpty()) 
			{
				postGrid.getSortModel().clear();
				postGrid.getSortModel().updateSort("creationDate");
				
		}
	}
	
}
