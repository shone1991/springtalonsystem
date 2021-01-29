package com.ivc.talonsystem.testdao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.AbstractCompanyDao;
import com.ivc.talonsystem.dao.RjuDao;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Vstan;
import com.ivc.talonsystem.testconfig.TestHibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestHibernateConfiguration.class })
public class TestAbstractCompanyDao {

	@Autowired
	private AbstractCompanyDao abstractCompanyDao;

	@Autowired
	private RjuDao rjuDao;


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
	public void saveAbstracCompanyTest() {
		AbstractCompany abs = new AbstractCompany();
		abs.setCallname("abstract");
		abstractCompanyDao.save(abs);
	}

	@Test
	@Transactional
	public void findTest() {
		Rju rjufromrjudao = rjuDao.findByCallname("РЖУ Ташкент");
		System.out.println("rju: " + rjufromrjudao.toString());
		assertNotNull(rjufromrjudao);

		Rju rjufromabstcompdao = (Rju) abstractCompanyDao.findByCallName("РЖУ Ташкент");
		System.out.println("rju from abstr" + rjufromabstcompdao.getBriefname());

	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveUnitDepartTest() {
		UnitDepart abs = new UnitDepart();
		abs.setCallname("unitdepart");
		abs.setNameUnit(abs.getCallname());
		abstractCompanyDao.save(abs);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveUnderUnitDepartTest() {
		UnitDepart unitdep = (UnitDepart) abstractCompanyDao.findByCallName("Финанс");
		System.out.println("UNITDEP ----"+unitdep.getCallname());
		Underunitdep abs = new Underunitdep();
		abs.setCallname("underunitdepart");
		abs.setName(abs.getCallname());
		abs.setUnitdep(unitdep);
		abstractCompanyDao.save(abs);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveVstanTest() {

		Rju otd = (Rju) abstractCompanyDao.findByCallName("РЖУ Ташкент");

		Vstan st = new Vstan();
		st.setKod(777777);
		st.setCallname("new station");
		st.setName(st.getCallname());
		st.setOtd(otd);
		abstractCompanyDao.save(st);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveUnderrjuTest() {

		Rju otd = (Rju) abstractCompanyDao.findByCallName("РЖУ Ташкент");

		Underrju underrju = new Underrju();
		underrju.setCallname("underrju");
		underrju.setName(underrju.getCallname());
		underrju.setRju(otd);
		abstractCompanyDao.save(underrju);
	}
}
