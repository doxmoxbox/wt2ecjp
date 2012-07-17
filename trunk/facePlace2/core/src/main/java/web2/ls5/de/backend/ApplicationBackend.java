package web2.ls5.de.backend;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;

public interface ApplicationBackend {

  /*
   * Neu von Emil
   */
  public void endFriendship(UserPerson shooter, UserPerson shooted);

  public UserPerson login(String username, String passwort);

  public Vector<UserPerson> searchNames(String prefix);

  public void invite(UserPerson inviter, UserPerson invitee);

  public List<Invitation> chooseInivitation(UserPerson invitee);

  public void startFriendship(Invitation invitation);

  public UserPerson register(String username, String passwort, String name);

  public void post(UserPerson poster, String text);

  public List<Posting> getPostsWall(UserPerson user);

  /*
   * Test all
   */
  public String testAll();

  /*
   * All about people:
   */
  public DBPerson createNewPerson();

  public Set<DBPerson> getAllPersons();

  public DBPerson getPerson(long id);

  public void removePerson(long id);

  public Set<DBPerson> getFriends(DBPerson pers);

  public Set<DBPerson> findPersons(String prefix);

  public void createInvitation(DBPerson src, DBPerson invitedPerson);

  public Set<Invitation> getInvitations(DBPerson pers);

  public void acceptInvitation(Invitation i);

  public void declineInvitation(Invitation i);

  public DBPerson findDBPersonByName(String name);

  public void removeFriend(DBPerson a, DBPerson b);

  public Invitation getInvitationById(long id);

  /*
   * All about posts:
   */
  public Set<DBPost> getAllPosts();

  public Set<DBPost> getAllPostsFromPerson(long id);

  public DBPost createNewPost();

  public void removePost(long id);

  public DBPost getPost(long id);

  public void createPosting(DBPerson pers, String text);

  public Set<DBPost> getAllPostingsFromDBPerson(DBPerson pers);

  /*
   * Something other
   */
  public UserManager getUserManager();

  public void createTestEntries(DBPerson pers);

  public String sayHello();
}
