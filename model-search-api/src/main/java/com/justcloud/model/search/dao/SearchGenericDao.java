package com.justcloud.model.search.dao;

import com.justcloud.model.dao.GenericDao;
import com.justcloud.model.domain.DomainObject;

public interface SearchGenericDao<T extends DomainObject> extends GenericDao<T> {

	void indexEntity(T object);

	void indexAllEntities();

	void deleteAllIndexes();

	void deleteIndex(T object);

}
