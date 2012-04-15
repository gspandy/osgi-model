package com.justcloud.model.test.dao.jpa;

import com.justcloud.model.dao.jpa.GenericDaoHibernateJpa;
import com.justcloud.model.test.dao.TestPersonDao;
import com.justcloud.model.test.domain.TestPerson;
import com.justcloud.model.test.domain.jpa.TestPersonJpa;

public class TestPersonDaoJpa extends GenericDaoHibernateJpa<TestPerson, TestPersonJpa>
		implements TestPersonDao {

	public TestPersonDaoJpa() {
		super(TestPersonJpa.class);
	}

}
