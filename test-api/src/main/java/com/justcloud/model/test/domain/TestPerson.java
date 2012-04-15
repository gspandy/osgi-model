package com.justcloud.model.test.domain;

import com.justcloud.model.domain.DomainObject;

public interface TestPerson extends DomainObject {

	void setId(Long id);

	String getName();

	String getNickname();

	String getEmail();

}
