package web2.ls5.de.pages;

import org.apache.tapestry5.annotations.Persist;
import web2.ls5.de.beans.Person;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 04.05.12
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public class Beanedit {

	@Persist
	private Person p;



	public Person getPerson() {

		if(this.p == null) {
			this.p = new Person();


			this.p.setName("Hans-Maria von Schnitzel");
			this.p.setAlter(73);
		}


		return this.p;
	}

}
