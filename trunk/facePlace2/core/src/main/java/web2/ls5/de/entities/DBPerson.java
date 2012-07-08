package web2.ls5.de.entities;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.util.Date;

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
	private Date birthday;

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
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
