package web2.ls5.de.biz.entities.atoms;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Named
@Entity
public class Invitation implements Serializable {

  private long id;
  UserPerson inviter;
  UserPerson invitee;

  public Invitation() {
    // TODO Auto-generated constructor stub
  }

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UserPerson getInviter() {
    return inviter;
  }

  public void setInviter(UserPerson inviter) {
    this.inviter = inviter;
  }

  @ManyToOne
  public UserPerson getInvitee() {
    return invitee;
  }

  public void setInvitee(UserPerson invitee) {
    this.invitee = invitee;
  }

  public void approve() {
    inviter.getFriendsList().add(invitee);
    invitee.getFriendsList().add(inviter);
  }
}
