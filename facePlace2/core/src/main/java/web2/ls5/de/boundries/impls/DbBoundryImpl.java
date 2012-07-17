package web2.ls5.de.boundries.impls;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import web2.ls5.de.biz.entities.atoms.Invitation;
import web2.ls5.de.biz.entities.atoms.Posting;
import web2.ls5.de.biz.entities.atoms.UserPerson;
import web2.ls5.de.boundries.DbBoundry;
import web2.ls5.de.persistence.Persistence;

/**
 * DbBoundyImpl
 * Copyright: TU Dortmund - Webtech2 - SS 2012
 * @created 16.07.2012 - 18:39:23
 */
public class DbBoundryImpl implements DbBoundry {

  @Inject
  @Persistence
  EntityManager em;

  public DbBoundryImpl() {
  }

  public UserPerson getUserByUsername(String username) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    UserPerson pers = null;
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(username)) {
        pers = p;
      }
    }
    return pers;
  }

  public List<Invitation> getUsersInvitations(UserPerson user) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(user.getName())) {
        return p.getInvitationsList();
      }
    }
    return null;
  }

  public List<Posting> getPostingsWall(UserPerson user) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(user.getName())) {
        return p.getPostingsList();
      }
    }
    return null;
  }

  public List<UserPerson> getFriends(UserPerson user) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(user.getName())) {
        return p.getFriendsList();
      }
    }
    return null;
  }

  public void addInvitation(UserPerson user, Invitation invitation) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(user.getName())) {
        p.getInvitationsList().add(invitation);
      }
    }
  }

  public void addPosting(UserPerson user, Posting posting) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(user.getName())) {
        p.getPostingsList().add(posting);
      }
    }
  }

  public void addFriend(UserPerson liked, UserPerson liker) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(liked.getName())) {
        p.getFriendsList().add(liker);
      }
      if (p.getName().equals(liker.getName())) {
        p.getFriendsList().add(liked);
      }
    }
  }

  public void removeInvitation(UserPerson user, Invitation invitation) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(user.getName())) {
        p.getInvitationsList().remove(invitation);
      }
    }
  }

  public void removeFriend(UserPerson hater, UserPerson hated) {
    Query q = em.createQuery("SELECT p FROM DBPerson p", UserPerson.class);
    for (UserPerson p : new HashSet<UserPerson>(q.getResultList())) {
      if (p.getName().equals(hater.getName())) {
        p.getFriendsList().add(hated);
      }
      if (p.getName().equals(hated.getName())) {
        p.getFriendsList().add(hater);
      }
    }
  }
}
