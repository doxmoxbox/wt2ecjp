package web2.ls5.de.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 08.05.12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class DBPerson implements Serializable {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String password;
  @Temporal(value = TemporalType.DATE)
  private Date birthdate;

  public DBPerson() {
  }

  public DBPerson(
          String name,
          String hashedPassword,
          Date birthdate) {
    Objects.requireNonNull(hashedPassword, "hashedPassword is null");
    Objects.requireNonNull(name, "name is null");
    Objects.requireNonNull(birthdate, "birthdate is null");
    this.name = name;
    password = hashedPassword;
    this.birthdate = birthdate;
  }

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
}
