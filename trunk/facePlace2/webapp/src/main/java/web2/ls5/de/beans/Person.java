package web2.ls5.de.beans;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 04.05.12
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class Person {

	private String name;
	private int alter;
	private Date geburtstag;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}
}
