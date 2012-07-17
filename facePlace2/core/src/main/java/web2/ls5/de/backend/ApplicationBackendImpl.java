package web2.ls5.de.backend;

import java.util.*;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import web2.ls5.de.biz.controller.SearchEngineController;
import web2.ls5.de.biz.controller.UserStatusController;
import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;
import web2.ls5.de.biz.entities.atoms.WeAreFriends;
import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;
import web2.ls5.de.persistence.Persistence;

@Named("applicationBackend")
@RequestScoped
public class ApplicationBackendImpl implements ApplicationBackend {

  @Inject
  @Persistence
  EntityManager em;
  private UserManager um;
  UserStatusController us = new UserStatusController();
  SearchEngineController se = new SearchEngineController();

  /*
   * -----------------------------------
   * All about user: --
   * -----------------------------------
   */
  public void endFriendship(UserPerson shooter, UserPerson shooted) {
    shooter.endFrindship(shooted);
  }

  public UserPerson login(String username, String passwort) {
    return us.login(username, passwort);
  }

  public Vector<UserPerson> searchNames(String prefix) {
    return se.searchNames(prefix);
  }

  public void invite(UserPerson inviter, UserPerson invitee) {
    inviter.invite(invitee);
  }

  public List<Invitation> chooseInivitation(UserPerson invitee) {
    return invitee.getInvitationsList();
  }

  public void startFriendship(Invitation invitation) {
    invitation.approve();
  }

  public UserPerson register(String username, String passwort, String name) {
    return us.register(username, passwort, name);
  }

  public DBPerson createNewPerson() {
    DBPerson newperson = new DBPerson();

    em.persist(newperson);

    return newperson;
  }

  public Set<DBPerson> getAllPersons() {
    Query q = em.createQuery("SELECT p FROM DBPerson p", DBPerson.class);
    return new HashSet<DBPerson>(q.getResultList());
  }

  public DBPerson getPerson(long id) {
    return em.find(DBPerson.class, id);
  }

  public void removePerson(long id) {
    DBPerson p = getPerson(id);
    if (p != null) {
      em.remove(p);
    }
  }

  /*
   * ---------------------------
   * All about posts:	--
   * ---------------------------
   */
  /**
   * Use this!
   */
  public void createPosting(DBPerson pers, String text) {
    DBPost post = new DBPost();
    post.setCreationDate(new Date());
    post.setCreator(pers.getName());
    post.setMsg(text);
    em.persist(post);
  }

  /**
   * Use this!
   */
  public Set<DBPost> getAllPostingsFromDBPerson(DBPerson pers) {
    Query q = em.createQuery("SELECT p FROM DBPost p", DBPost.class);
    SortedSet<DBPost> list = new TreeSet<DBPost>();
    for (DBPost p : new HashSet<DBPost>(q.getResultList())) {
      if (pers.getName().equals(p.getCreator())) {
        list.add(p);
      }
    }

    Query friends = em.createQuery("SELECT f FROM WeAreFriends f", WeAreFriends.class);
    for (WeAreFriends war : new HashSet<WeAreFriends>(friends.getResultList())) {
      if (pers.getName().equals(war.getFriendOne())) {
        for (DBPost p : new HashSet<DBPost>(q.getResultList())) {
          if (war.getFriendOne().equals(p.getCreator())) {
            list.add(p);
          }
        }
      } else if (pers.getName().equals(war.getFriendTwo())) {
        for (DBPost p : new HashSet<DBPost>(q.getResultList())) {
          if (war.getFriendTwo().equals(p.getCreator())) {
            list.add(p);
          }
        }
      }      
    }
    return new TreeSet<DBPost>(list);
  } 

  

  public void post(UserPerson poster, String text) {
    poster.postOnAllWalls(text);
  }

  public List<Posting> getPostsWall(UserPerson user) {
    return user.getPostingsList();
  }

  public Set<DBPost> getAllPosts() {
    Query q = em.createQuery("SELECT p FROM DBPost p", DBPost.class);
    return new HashSet<DBPost>(q.getResultList());
  }

  public Set<DBPost> getAllPostsFromPerson(long id) {
    Query q = em.createQuery("SELECT p FROM DBPost p WHERE p.creator = " + id, DBPost.class);
    return new HashSet<DBPost>(q.getResultList());
  }

  /**
   * Creates new post, stores in DB and returns it.
   *
   * @return New created post.
   */
  public DBPost createNewPost() {
    DBPost newPost = new DBPost();
    em.persist(newPost);
    return newPost;
  }

  /**
   * Removes post by given id.
   */
  public void removePost(long id) {
    DBPost p = getPost(id);
    if (p != null) {
      em.remove(p);
    }
  }

  /**
   * Returns post by given id.
   */
  public DBPost getPost(long id) {
    return em.find(DBPost.class, id);
  }

  /*
   * ---------------------------
   * Something other:	--
   * ---------------------------
   */
  public String sayHello() {
    return "Hello!";
  }

  public String testAll() {
    return "huhu";
    //return gbi.testAll();
  }

  /**
   * Absolut ueberfluessig!
   */
  public UserManager getUserManager() {
    if (um != null) {
      return um;
    } else {
      um = new UserManager(em);
      return um;
    }
  }

  public void createTestEntries() {
    for (int i = 0; i < 15; i++) {
      DBPerson newPerson = createNewPerson();
      newPerson.setName("Mr.X");
      newPerson.setBirthdate(new Date((int) Math.random() * 10, (int) Math.random() * 10, (int) Math.random() * 10));
      newPerson.setPassword(getUserManager().getHashedPassword("123"));

      DBPost newPost = new DBPost();
      Date bla = new Date((int) Math.random() * 10, (int) Math.random() * 10, (int) Math.random() * 10);
      newPost.setCreationDate(bla);
      newPost.setMsg(generateRandomString());
      newPost.setCreator(newPerson.getName());

      em.persist(newPost);
    }
  }

  private String generateRandomString() {
    return java.util.UUID.randomUUID().toString();
  }
}
