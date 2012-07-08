package web2.ls5.de.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;
import org.apache.tapestry5.alerts.AlertManager;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

public class Postings
{
	@Inject
	ApplicationBackend backend;
	
	@Inject
	private AlertManager alertManager;
	
	@Property
	private DBPost post;
	
	@Property
	private DBPerson person;
	
	void onActionFromCreateTestEntries()
    {
        alertManager.info("Erstelle Testeintraege");
        backend.createTestEntries();
        alertManager.info("Fertig damit!");
    }
	
	public Set<DBPost> getAllPosts()
	{
		return backend.getAllPosts();
	}
	
	public Set<DBPerson> getAllPersons()
	{
		return backend.getAllPersons();
	}
	
	
	public String getHello()
	{
		return backend.sayHello();
	}
}