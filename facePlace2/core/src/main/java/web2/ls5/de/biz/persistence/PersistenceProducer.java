package web2.ls5.de.biz.persistence;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Responsible for the production of {@link javax.persistence.EntityManager}s.
 */
@Stateless
public class PersistenceProducer {

	/**
	 * The injected {@link javax.persistence.EntityManager}.
	 */
	private EntityManager entityManager;

	/**
	 * Produce an {@link javax.persistence.EntityManager}.
	 *
	 * @return an {@link javax.persistence.EntityManager}.
	 */
	@Produces
	@Persistence
	@RequestScoped
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * Injection point for the {@link javax.persistence.EntityManager}.
	 *
	 * @param entityManager
	 *            an {@link javax.persistence.EntityManager}.
	 */
	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
