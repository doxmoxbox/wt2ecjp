package webtech2.faceplace.beans;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


/**
 * Person
 * Copyright: TU Dortmund - Webtech2 - SS 2012
 * @created 07.06.2012 - 10:33:04
 */
@Entity
public class Person implements Serializable {

  private String name;
  private Date birthdate;
  private long id;
  private String password;
  private String gender;
  private Set<Person> friends;
  
  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  
  /**
   * Creates an empty Person.
   */
  public Person() {
    
  }
  
  /**
   * Creates a new person.
   * 
   * @param name
   * @param hashedPassword
   * @param birthdate
   * @param gender 
   */
  public Person(String name,
          String hashedPassword,
          Date birthdate,
          String gender) {
    Objects.requireNonNull(hashedPassword, "hashedPassword is null");
    Objects.requireNonNull(name,"name is null");
    Objects.requireNonNull(birthdate,"birthdate is null");
    Objects.requireNonNull(gender,"gender is null");
    this.name = name;
    password = hashedPassword;
    this.birthdate = birthdate;
    this.gender = gender;
    friends = new HashSet<>();
  }
  
  /**
   * Adds a new friend to this user.
   * 
   * @param newFriend The new friend.
   */
  public void addFriend(Person newFriend) {
    Objects.requireNonNull(newFriend, "newFriend is null");
    friends.add(newFriend);
  }
  
  /**
   * Returns the friends of this user.
   * 
   * @return This users friends as a Set.
   */
  @OneToMany
  public List<Person> getFriends() {
    List<Person> sortedFriends = new LinkedList(friends);
    Collections.sort(sortedFriends, new FriendComparator());
    return sortedFriends;
  }
  
  /**
   * Updates the friends.
   * 
   * @param friends New Set of friends.
   */
  public void setFriends(org.hibernate.collection.internal.PersistentBag friends) {
    this.friends.clear();
    for(Object curObject : friends) {
      this.friends.add((Person)curObject);
    }
  }

  /**
   * Returns the hashed password.
   * 
   * @return The password.
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * Sets the password.
   * 
   * @param password The new password.
   */
  public void setPassword(String password) {
    Objects.requireNonNull(password,"password is null");
    this.password = password;
  }
  
  /**
   * Returns the name.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name The new name.
   */
  public void setName(String name) {
    Objects.requireNonNull(name,"name is null");
    this.name = name;
  }

  /**
   * Returns the gender.
   * 
   * @return Male or femaile
   */       
  public String getGender() {
    return gender;
  }
  
  /**
   * Sets the gender.
   * 
   * @param gender The new gender.
   */
  public void setGender(String gender) {
    Objects.requireNonNull(gender,"gender is null");
    this.gender = gender;
  }

  /**
   * Returns the birthdate.
   *
   * @return The birthdate.
   */
  @Temporal(javax.persistence.TemporalType.DATE)
  public Date getBirthdate() {
    return birthdate;
  }

  /**
   * Sets the birthdate.
   *
   * @param birthdate The new birthdate.
   */
  public void setBirthdate(Date birthdate) {
    Objects.requireNonNull(birthdate, "birthdate is null");
    this.birthdate = birthdate;
  }
  
  /**
   * A Comparator that sorts friends according to their names.
   */
  private class FriendComparator implements Comparator<Person> {

    public FriendComparator() {
      
    }
    
    @Override
    public int compare(Person a, Person o) {
      if(a.getName().compareToIgnoreCase(o.getName()) < 0) {
        return -1;
      }
      else {
        return 1;
      }
    }
    
  }
}
