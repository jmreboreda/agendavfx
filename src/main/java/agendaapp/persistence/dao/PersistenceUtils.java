package agendaapp.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class PersistenceUtils {

	private static final String PERSISTENCE_UNIT_NAME = "agendaapp";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		if(entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

}
