package web2.ls5.de.biz.entities.atoms;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;


@Entity
public class UserPerson implements Comparable<UserPerson>, Serializable {

  private String username;
  private String passwort;
  private String name;
  @ElementCollection
  @OneToMany
  List<UserPerson> friends;
  @ElementCollection
  @OneToMany
  List<Posting> postingsList;
  @ElementCollection
  @OneToMany
  List<Invitation> invitationsList;
  @Id
  @GeneratedValue
  private long id;

  public UserPerson() {
    // TODO Auto-generated constructor stub
    this("dummyUsername", "DummyPasswort", "DummyName");
  }

  public UserPerson(String username, String passwort, String name) {
    this.username = username;
    this.passwort = passwort;
    this.name = name;
    friends = new LinkedList<UserPerson>();
    postingsList = new LinkedList<Posting>();
    invitationsList = new LinkedList<Invitation>();
  }

  public int compareTo(UserPerson u) {
    // TODO Auto-generated method stub
    if (this.getName().compareTo(u.getName()) < 0) {
      return -1;
    }
    if (this.getName().compareTo(u.getName()) > 0) {
      return 1;
    }
    return 0;
  }

    public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswort() {
    return passwort;
  }

  public void setPasswort(String passwort) {
    this.passwort = passwort;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<UserPerson> getFriendsList() {
    return friends;
  }

  public void setFriends(List<UserPerson> friendsList) {
    friends = friendsList;
  }

  public List<Posting> getPostingsList() {
    return postingsList;
  }

  public void setPostingsList(List<Posting> postiongsList) {
    this.postingsList = postiongsList;
  }

  public List<Invitation> getInvitationsList() {
    return invitationsList;
  }

  public void setInvitationsList(List<Invitation> invitationsList) {
    this.invitationsList = invitationsList;
  }

 /* public void invite(UserPerson invitee) {
    Invitation inv = new Invitation();
    inv.setInviter(this);
    inv.setInvitee(invitee);
    invitee.getInvitationsList().add(inv);
  }

  public List<Invitation> chooseInvitation(UserPerson user) {
    return invitationsList;
  }

  public void startFriendship(Invitation invitation) {
    invitation.approve();
  }*/

  public void endFrindship(UserPerson notFriend) {
    friends.remove(notFriend);
    notFriend.getFriendsList().remove(this);
  }

  public void postOnAllWalls(String text) {
    Posting posting = new Posting();
    posting.setText(text);
    postOnMyWall(posting);
    for (UserPerson friend : friends) {
      friend.postOnMyWall(posting);
    }
  }

  public void postOnMyWall(Posting posting) {
    postingsList.add(posting);
  }
}
