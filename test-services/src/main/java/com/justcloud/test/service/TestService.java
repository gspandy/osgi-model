package com.justcloud.test.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.justcloud.model.test.dao.TestPersonDao;

public class TestService {

	private TestPersonDao testPersonDao;
	
	public void setTestPersonDao(TestPersonDao testPersonDao) {
		Logger.getLogger("TestService").log(Level.INFO,
				"HOLA " + testPersonDao.toString());
	}
	
	public void test() {
		
	}

}
