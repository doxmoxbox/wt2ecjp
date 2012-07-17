package web2.ls5.de.biz.entities.atoms;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Invitation implements Serializable {

  private long id;
  String inviter;
  String invitedPerson;

  public Invitation() {
    // TODO Auto-generated constructor stub
  }
  
  public Invitation(String a, String b) {
    inviter = a;
    invitedPerson = b;
  }

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getInviter() {
    return inviter;
  }

  public void setInviter(String inviter) {
    this.inviter = inviter;
  }

  public String getInvitee() {
    return invitedPerson;
  }

  public void setInvitee(String invitee) {
    this.invitedPerson = invitee;
  }
}
