package web2.ls5.de.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.util.Date;

@Entity
public class DBPost implements Serializable, Comparable<DBPost> {

  private long id;
  private String msg;
  private Date creationDate;
  private String creator;

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Temporal(value = TemporalType.TIMESTAMP)
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getCreator() {
    return creator;
  }

  public int compareTo(DBPost o) {

    if (creationDate.getTime() < o.getCreationDate().getTime()) {
      return 1;
    } else {
      return -1;
    }
  }
}
