package com.justcloud.test.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.justcloud.model.test.dao.TestPersonDao;

public class TestService {

	private ScheduledExecutorService executor;

	private final Logger logger = LoggerFactory.getLogger(TestService.class);

	private TestPersonDao testPersonDao;

	public void test() {
		logger.info("This is ready " + testPersonDao.getAll().size());
	}

	public void setTestPersonDao(TestPersonDao testPersonDao) {
		this.testPersonDao = testPersonDao;
	}

	public void setup() {
		executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(new Runnable() {

			public void run() {
				test();
			}
		}, 10, 10, TimeUnit.SECONDS);
	}

	public void teardown() {
		executor.shutdown();
	}

}
