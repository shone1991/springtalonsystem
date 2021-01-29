package com.ivc.talonsystem.testdao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.UserDao;
import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.testconfig.TestHibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestHibernateConfiguration.class })
public class TestUserDao {

	@Autowired
	private UserDao userDao;
	
	

	@Test
	@Transactional
	@Rollback(true)
	public void findUserTest() {
		User u=userDao.findBySSO("sam");
		assertNotNull(u);
		System.out.println("classname: "+u.getCompany().getClass().getSimpleName());
	}




}
