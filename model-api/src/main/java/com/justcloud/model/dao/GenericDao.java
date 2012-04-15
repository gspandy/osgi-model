package com.justcloud.model.dao;

import java.util.List;

import com.justcloud.model.domain.DomainObject;

public interface GenericDao<T extends DomainObject> {

	T get(Long id);

	List<T> getAll();

	void save(T object);

	void delete(T object);

}
