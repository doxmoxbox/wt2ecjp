package web2.ls5.de.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import web2.ls5.de.entities.DBPerson;

public class FaceFind 
{
	@Property
	@SessionState(create=false)
	private DBPerson loggedInPerson;
	
	Object onActivate()
	{
		if (loggedInPerson == null)return "index";
		return null;
	}
}
