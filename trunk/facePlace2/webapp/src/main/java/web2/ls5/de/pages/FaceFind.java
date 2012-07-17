package web2.ls5.de.pages;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextArea;
import org.apache.tapestry5.ioc.annotations.Inject;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.biz.entities.atoms.Invitation;

import web2.ls5.de.entities.DBPerson;

public class FaceFind {

  @Inject
  private ApplicationBackend backend;
  @Property
  private Form searchForm;
  @Property
  private Form resultForm;
  @Property
  @SessionState(create = false)
  private DBPerson loggedInPerson;
  @Property
  private TextArea textArea;
  @Property
  private String txt;
  @Property
  private Set<String> searchResult = new HashSet<String>();
  @Property
  private String chosenPerson;
  
  @Property
  private Invitation invite;

  
  void onActionFromAddFriend(long invId)
  {
      backend.acceptInvitation(backend.getInvitationById(invId));
  }  
  
  void onActionFromIgnorFriend(long invId)
  {
	  backend.declineInvitation(backend.getInvitationById(invId));
  }  
  
  
  public Set<Invitation> getInvites()
  {
	  return backend.getInvitations(loggedInPerson);
  }
  
  
  void onValidateFromSearchForm() {   
    if (txt != null) {
      Set<DBPerson> result = backend.findPersons(txt);
      if (!result.isEmpty()) {
        for (DBPerson p : result) {
          if (!loggedInPerson.getName().equals(p.getName())) {
            searchResult.add(p.getName());
          }
        }
      }
    }
  }

  Object onActivate() {
    if (loggedInPerson == null) {
      return "index";
    }
    return null;
  }

  void onValidateFromResultForm() {
    if (chosenPerson != null) {
      chosenPerson = chosenPerson.replace("[", "");
      chosenPerson = chosenPerson.replace("]", "");
      backend.createInvitation(loggedInPerson, backend.findDBPersonByName(chosenPerson));
      chosenPerson = null;
      searchResult.clear();
    }
  }
}
