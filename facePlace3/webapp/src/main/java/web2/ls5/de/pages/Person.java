package web2.ls5.de.pages;


import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.entities.DBPerson;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

public class Person
{

	@Inject
	ApplicationBackend backend;

	@Property
	private DBPerson person;

	@Persist
	@Property
	private DBPerson editperson;


	@Persist
	@Property
	private DBPerson addperson;

	public String getHello() {
		return backend.sayHello();
	}


	public Set<DBPerson> getAllPersons() {
		return backend.getAllPersons();
	}


	void onActionFromRemovePerson(long id) {
		backend.removePerson(id);
	}

	void onActionFromAddPerson() {
		addperson = new DBPerson();
		editperson = null;
	}

	void onActionFromEditPerson(long id) {
		addperson = null;
		editperson = backend.getPerson(id);
	}

	void onSuccessFromEdit() {
		DBPerson p = backend.getPerson(editperson.getId());
		p.setName(editperson.getName());
		p.setBirthday(editperson.getBirthday());

		// set to null to make editor disappear on page
		editperson = null;
	}

	void onSuccessFromAdd() {
		DBPerson p = backend.createNewPerson();

		p.setName(addperson.getName());
		p.setBirthday(addperson.getBirthday());

		// set to null to make editor disappear on page
		addperson = null;
	}


}
