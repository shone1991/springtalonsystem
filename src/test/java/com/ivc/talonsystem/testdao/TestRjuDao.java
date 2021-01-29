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
import com.ivc.talonsystem.dao.RjuDao;
import com.ivc.talonsystem.dao.ViolationDao;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.testconfig.TestHibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestHibernateConfiguration.class })
public class TestRjuDao {

	@Autowired
	private AbstractCompanyDao abstractCompanyDao;

	@Autowired
	private RjuDao rjuDao;
	
	@Autowired
	private ViolationDao violationDao;

	@Test
	@Transactional
	@Rollback(true)
	public void saveTest() {
		Rju rju = new Rju();
		rju.setBriefname("a");
		rju.setCallname("aaaaaa");
		rju.setNamerju(rju.getCallname());

		rjuDao.save(rju);
	}


	@Test
	@Transactional
	@Rollback(true)
	public void findTest() {
		Rju rjufromrjudao = rjuDao.findByCallname("РЖУ Ташкент");
		System.out.println("rju: " + rjufromrjudao.toString());
		assertNotNull(rjufromrjudao);

		Rju rjufromabstcompdao = (Rju) abstractCompanyDao.findByCallName("РЖУ Ташкент");
		System.out.println("rju from abstr" + rjufromabstcompdao.getBriefname());

	}
	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void findAllviolTest() {
//		Rju rjufromrjudao = rjuDao.findByCallname("РЖУ Ташкент");
//		List<Violation> list=violationDao.findAllViolations(rjufromrjudao);
//		System.out.println("SIZE "+list.size());
//		assertTrue(list.size()==9);
//
//
//	}

}
