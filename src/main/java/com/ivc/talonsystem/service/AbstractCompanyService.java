package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.DTO.AbstractCompanyDTO;
import com.ivc.talonsystem.entity.AbstractCompany;


public interface AbstractCompanyService {
	AbstractCompany findById(int id);

	AbstractCompany findByCallName(String callname);

	void saveAbstractCompany(AbstractCompany abstractCompany);

	void updateAbstractCompany(AbstractCompany abstractCompany);

	void deleteAbstractCompanyByCallname(String callname);
	
	void deleteAbstractCompanyById(int id);

	List<AbstractCompany> findAllAbstractCompanys();
	
	List<AbstractCompanyDTO> findAllAbstractCompaniesWhereNameLike(String namelike);
	
	List<AbstractCompany> listChildCompanyOfParticularCompany(AbstractCompany company);
	
	List<AbstractCompanyDTO> listChildCompanyOfParticularCompany(AbstractCompany company, String namelike);

}
