package com.ivc.talonsystem.dao;

import java.util.Date;
import java.util.List;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.ConclusionViolGuide;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.entity.Violguide;

public interface ViolationDao {
	Violation findById(int id);

	void save(Violation violation);

	void edit(Violation violation);

	void delete(int id);
	
	List<Violation> findAllViolations();
	
	List<Violation> findAllViolations(AbstractCompany company, Integer offset, Integer maxResult);
	
	List<Violation> findAllViolations(AbstractCompany company, Date d1, Date d2, Integer offset, Integer maxResult);
	
	List<Violation> findAllViolations(AbstractCompany company);

	List<Violation> findAllViolations(List<AbstractCompany> listcompany, String surname, Integer stubn, Date dateseize1, Date dateseize2);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Integer stubn, Date dateseize1, Date dateseize2);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2, ConclusionViolGuide conc);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2, Violguide violguide, int stubnumber);
}
