package web2.ls5.de.backend;

import web2.ls5.de.entities.DBPerson;
import web2.ls5.de.entities.DBPost;
import web2.ls5.de.persistence.Persistence;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 09.05.12
 * Time: 08:12
 * To change this template use File | Settings | File Templates.
 */
@Named("applicationBackend")
@RequestScoped
public class ApplicationBackendImpl implements ApplicationBackend {

	@Inject
	@Persistence
	EntityManager em;

	@Override
	public String sayHello() {
		return "Hello!";
	}

	@Override
	public DBPerson createNewPerson() {
		DBPerson newperson = new DBPerson();

		em.persist(newperson);

		return newperson;
	}

	@Override
	public Set<DBPerson> getAllPersons() {

		Query q = em.createQuery("SELECT p FROM DBPerson p", DBPerson.class);

		return new HashSet<DBPerson>(q.getResultList());
	}
	
	/*@Override
	public Set<DBPost> getAllPosts() {

		Query q = em.createQuery("SELECT p FROM DBPost p", DBPost.class);

		return new HashSet<DBPost>(q.getResultList());
	}*/

	@Override
	public DBPerson getPerson(long id) {
		return em.find(DBPerson.class, id);
	}

	@Override
	public void removePerson(long id) {
		DBPerson p = getPerson(id);
		if(p != null) {
			em.remove(p);
		}
	}

}
