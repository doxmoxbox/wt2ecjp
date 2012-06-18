package webtech2.faceplace.persistence;

import java.lang.annotation.Annotation;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * PersistenceProducer
 * Copyright: TU Dortmund - Webtech2 - SS 2012
 * @created 07.06.2012 - 11:42:22
 */
/**
 * Responsible for the production of {@link javax.persistence.EntityManager}s.
 */
@Stateless
public class PersistenceProducer{

  @PersistenceUnit
  EntityManagerFactory emf;
  /**
   * The injected {@link javax.persistence.EntityManager}.
   */
  EntityManager entityManager = emf.createEntityManager();

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
   * an {@link javax.persistence.EntityManager}.
   */
  @PersistenceContext
  public void setEntityManager(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }
}
