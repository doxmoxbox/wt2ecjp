package web2.ls5.de.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.entities.DBPerson;


/**
 * Start page of application demoprojekt.
 */
public class Index
{
	@Property
	@SessionState(create=false)
	private DBPerson loggedInPerson;
	
	@Inject
	private ApplicationBackend backend;
	
	public String getGreeting()
	{
		return "Willkommen auf facePlace++!";
	}
}
