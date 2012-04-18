package com.justcloud.model.search.jpa;

import java.util.List;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import com.justcloud.model.dao.jpa.GenericDaoHibernateJpa;
import com.justcloud.model.domain.DomainObject;
import com.justcloud.model.search.dao.SearchGenericDao;

public class SearchGenericDaoHibernateJpa<T extends DomainObject, V extends T>
		extends GenericDaoHibernateJpa<T, V> implements SearchGenericDao<T> {

	private final Class<V> type;

	protected SearchGenericDaoHibernateJpa(final Class<V> type) {
		super(type);
		this.type = type;
	}

	@Override
	public void deleteAllIndexes() {
		getFullTextEntityManager().purgeAll(type);
	}

	@Override
	public void deleteIndex(T entity) {
		getFullTextEntityManager().purge(type, entity.getId());
	}

	@Override
	public void indexAllEntities() {
		final FullTextEntityManager fullTextEntityManager = getFullTextEntityManager();
		final List<T> results = getAll();
		int counter = 0;
		final int numItemsInGroup = 10;
		for (final T result : results) {
			fullTextEntityManager.index(fullTextEntityManager.merge(result));
			if (counter++ % numItemsInGroup == 0) {
				fullTextEntityManager.flushToIndexes();
				fullTextEntityManager.clear();
			}
		}
	}

	@Override
	public void indexEntity(T entity) {
		getFullTextEntityManager().index(entity);
	}
	
	protected FullTextEntityManager getFullTextEntityManager() {
		return Search.getFullTextEntityManager(entityManager);
	}

}
