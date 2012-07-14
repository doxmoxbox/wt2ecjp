package web2.ls5.de.pages;

import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.entities.DBPerson;


public class About
{
  @Inject
	ApplicationBackend backend;
  
  @SessionState
  private long id;
  
	private DBPerson loggedInPerson;
  private Logger log = Logger.getLogger(About.class.getName());
  
  public DBPerson getLoggedInPerson() {
    Set<DBPerson> persons = backend.getAllPersons();
    for(DBPerson p : persons) {
      if(p.getId() == id) {
       loggedInPerson = p;
       return loggedInPerson;
      }
    }
    return null;
  }

  public void setLoggedInPerson(DBPerson pers) {
    loggedInPerson = pers;
  }
}
