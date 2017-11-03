package agendaapp.persistence.dao;

import agendaapp.utilities.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDAO<E, PK extends Serializable> {

	private final Class<E> entityClass;
	protected final Session session = HibernateUtil.retrieveGlobalSession();

	protected BaseDAO() {
		entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public E find(PK id) {
		return (E) session.get(entityClass, id);
	}

	public List<E> findAll() {
		return session.createQuery("from " + entityClass.getSimpleName()).list();
	}

	public void persist(E entity) {
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
	}

	public void merge(E entity) {
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
	}

	public void delete(E entity) {
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
	}


}
