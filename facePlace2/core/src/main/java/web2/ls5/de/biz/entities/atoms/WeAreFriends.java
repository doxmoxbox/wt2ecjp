package web2.ls5.de.biz.entities.atoms;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import web2.ls5.de.entities.DBPerson;

/**
 * WeAreFriends
 * Copyright: TU Dortmund - Webtech2 - SS 2012
 * @created 17.07.2012 - 15:11:26
 */
@Entity
public class WeAreFriends implements Serializable {
  
  private long id;
  private String friendOne;  
  private String friendTwo;
  
  public WeAreFriends() {
    
  }
  
  public WeAreFriends(String a, String b) {
    this.friendOne = a;
    this.friendTwo = b;
  }
  
  public String getFriendOne() {
    return friendOne;
  }

  public void setFriendOne(String friendOne) {
    this.friendOne = friendOne;
  }

  public String getFriendTwo() {
    return friendTwo;
  }

  public void setFriendTwo(String friendTwo) {
    this.friendTwo = friendTwo;
  }
  
  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
