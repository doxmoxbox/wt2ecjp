package web2.ls5.de.entities;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.Objects;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 08.05.12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class DBPerson {

	private long id;
	private String name;
	private String password;
	private Date birthdate;
	
	
	public DBPerson()
	{
		
	}
	
	
	public DBPerson(
			String name,
			String hashedPassword,
			Date birthdate) 
	{
		Objects.requireNonNull(hashedPassword, "hashedPassword is null");
	    Objects.requireNonNull(name, "name is null");
	    Objects.requireNonNull(birthdate, "birthdate is null");
	    this.name = name;
	    password = hashedPassword;
	    this.birthdate = birthdate;
	  }
	
	/**
	   * Adds a new friend to this user.
	   *
	   * @param newFriend The new friend.
	   */
	  /*public void addFriend(DBPerson newFriend) {
	    Objects.requireNonNull(newFriend, "newFriend is null");
	    friends.add(newFriend);
	  }*/
	  
	  /**
	   * Returns the friends of this user.
	   *
	   * @return This users friends as a Set.
	   */
	  /*@OneToMany
	  public List<DBPerson> getFriends() {
	    List<DBPerson> sortedFriends = new LinkedList(friends);
	    Collections.sort(sortedFriends, new FriendComparator());
	    return sortedFriends;
	  }*/
	  
	  /**
	   * Updates the friends.
	   *
	   * @param friends New Set of friends.
	   */
	  /*public void setFriends(org.hibernate.collection.internal.PersistentBag friends) {
	    this.friends.clear();
	    for (Object curObject : friends) {
	      this.friends.add((DBPerson) curObject);
	    }
	  }*/
	

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(value = TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthday) {
		this.birthdate = birthday;
	}
	
	public String getPassword() {
	    return password;
	  }
	
	/**
	   * Sets the password.
	   *
	   * @param password The new password.
	   */
	  public void setPassword(String password) {
	    Objects.requireNonNull(password, "password is null");
	    this.password = password;
	  }
	  
	  /**
	   * A Comparator that sorts friends according to their names.
	   */
	  private class FriendComparator implements Comparator<DBPerson> {

	    public FriendComparator() {
	    }

	    @Override
	    public int compare(DBPerson a, DBPerson o) {
	      if (a.getName().compareToIgnoreCase(o.getName()) < 0) {
	        return -1;
	      } else {
	        return 1;
	      }
	    }
	  }
}
