package com.ivc.talonsystem.testdao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.ViolationDao;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.testconfig.TestHibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestHibernateConfiguration.class })
public class TestViolationDao {
	
	@Autowired
	private ViolationDao violationDao; 

	@Test
	@Transactional
	@Rollback(true)
	public void findViolationTest() {
		Violation u=violationDao.findById(1);
		assertNotNull(u);
	}
}
