package com.ivc.talonsystem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.DTO.AbstractCompanyDTO;
import com.ivc.talonsystem.dao.AbstractCompanyDao;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Vstan;
import com.ivc.talonsystem.util.StringUtil;

@Service("abstractcompanyService")
@Transactional
public class AbstractCompanyServiceImpl implements AbstractCompanyService {
	@Autowired
	private AbstractCompanyDao abstractCompanyDao;

	@Override
	public AbstractCompany findById(int id) {
		return abstractCompanyDao.findById(id);
	}

	@Override
	public AbstractCompany findByCallName(String callname) {
		return abstractCompanyDao.findByCallName(callname);
	}

	@Override
	public void saveAbstractCompany(AbstractCompany abstractCompany) {
		abstractCompanyDao.save(abstractCompany);

	}

	@Override
	public void updateAbstractCompany(AbstractCompany abstractCompany) {
		abstractCompanyDao.edit(abstractCompany);

	}

	@Override
	public void deleteAbstractCompanyByCallname(String callname) {
		abstractCompanyDao.deleteByCallName(callname);

	}

	@Override
	public List<AbstractCompany> findAllAbstractCompanys() {
		return abstractCompanyDao.findAllAbstractCompanys();
	}

	@Override
	public void deleteAbstractCompanyById(int id) {
		abstractCompanyDao.deleteById(id);
		
	}

	@Override
	public List<AbstractCompanyDTO> findAllAbstractCompaniesWhereNameLike(String namelike) {
		List<AbstractCompanyDTO> abslist=findAllAbstractCompanys().stream()
				.map(c->new AbstractCompanyDTO(c.getId(), c.getCallname()))
				.filter(a -> StringUtil.containsIgnoreCase(a.getCallname(), namelike.trim()))
				.collect(Collectors.toList());
		return abslist;
	}
	
	@Override
	public List<AbstractCompany> listChildCompanyOfParticularCompany(AbstractCompany company) {
		Collection<AbstractCompany> companies = null;
		if (company instanceof Rju) {
			Rju comp=(Rju)company;
			companies = new ArrayList<AbstractCompany>();
			companies.addAll(comp.getvStanCollection());
			companies.addAll(comp.getUnderRjuCollection());
			companies.add(comp);
		} else if (company instanceof UnitDepart) {
			UnitDepart unit=(UnitDepart)company;
			companies = new ArrayList<AbstractCompany>();
			companies.addAll(unit.getUnderunitdepCollection());
			companies.add(unit);
		}else if (company instanceof Underunitdep) {
			companies = new ArrayList<AbstractCompany>();
			companies.add((Underunitdep)company);
		} else if (company instanceof Underrju) {
			companies = new ArrayList<AbstractCompany>();
			companies.add((Underrju)company);
		}else if (company instanceof Vstan) {
			companies = new ArrayList<AbstractCompany>();
			companies.add((Vstan)company);
		}else {
			companies = findAllAbstractCompanys();
		}
		
		return (List<AbstractCompany>) companies;
	}

	@Override
	public List<AbstractCompanyDTO> listChildCompanyOfParticularCompany(AbstractCompany company, String namelike) {
		List<AbstractCompanyDTO> abslist=listChildCompanyOfParticularCompany(company).stream()
				.map(c->new AbstractCompanyDTO(c.getId(), c.getCallname()))
				.filter(a -> StringUtil.containsIgnoreCase(a.getCallname(), namelike.trim()))
				.collect(Collectors.toList());
		return abslist;
	}

}
