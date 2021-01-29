package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.AbstractCompany;


public interface AbstractCompanyDao {
	AbstractCompany findById(int id);

	AbstractCompany findByCallName(String callname);

	void save(AbstractCompany AbstractCompany);
	
	void edit(AbstractCompany AbstractCompany);

	void deleteByCallName(String callname);
	
	void deleteById(int id);

	List<AbstractCompany> findAllAbstractCompanys();
	
}
