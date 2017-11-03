package agendaapp.persistence.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDAO<E, PK extends Serializable> {

	private final Class<E> entityClass;
	protected final EntityManager entityManager = PersistenceUtils.getEntityManager();

	protected BaseDAO() {
		entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public E find(PK id) {
		return (E) entityManager.find(entityClass, id);
	}

	public List<E> findAll() {
		return entityManager.createQuery("from " + entityClass.getSimpleName()).getResultList();
	}

	public void persist(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public void merge(E entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	public void delete(E entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}


}
