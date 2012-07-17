package web2.ls5.de.biz.entities.atoms;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Named
@Entity
public class Posting {

  private long id;
  String text;
  private UserPerson user;

  public Posting() {
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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @ManyToOne
  public UserPerson getUser() {
    return user;
  }

  public void setUser(UserPerson user) {
    this.user = user;
  }
}
