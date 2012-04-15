package com.justcloud.model.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.justcloud.model.dao.GenericDao;
import com.justcloud.model.domain.DomainObject;

public class GenericDaoHibernateJpa<T extends DomainObject, V extends T>
		implements GenericDao<T> {

	protected EntityManager entityManager;

	private final Class<V> type;

	protected GenericDaoHibernateJpa(final Class<V> type) {
		this.type = type;
	}

	public T get(Long id) {
		return entityManager.find(type, id);

	}

	public List<T> getAll() {
		return convertAll(entityManager.createQuery(
				"select obj from " + type.getName() + " obj", type)
				.getResultList());
	}

	public void save(T object) {
		entityManager.persist(object);
	}

	public void delete(T object) {
		entityManager.remove(object);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected List<T> convertAll(List<V> results) {
		return new ArrayList<T>(results);
	}

}
