package com.ivc.talonsystem.testdao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.AbstractCompanyDao;
import com.ivc.talonsystem.dao.PostJobDao;
import com.ivc.talonsystem.dao.RjuDao;
import com.ivc.talonsystem.dao.ViolationDao;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.PostJob;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.testconfig.TestHibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestHibernateConfiguration.class })
public class TestPostJob {
	
	@Autowired
	PostJobDao dao;

	@Test
	@Transactional
	@Rollback(true)
	public void findByPostNameTest() {
		PostJob job=dao.findByPostName("Авербандщик");
		assertNotNull(job);
	}


	

}
