package com.ivc.talonsystem.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.UnderunitdepDao;
import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.util.StringUtil;

@Service("underunitdepService")
@Transactional
public class UnderUnitDepServiceImpl implements UnderUnitDepService {

	@Autowired
	private UnderunitdepDao underunitDao;
	
	@Override
	public Underunitdep findById(int id) {
		return underunitDao.findById(id);
	}

	@Override
	public Underunitdep findByCallName(String callname) {
		return underunitDao.findByCallName(callname);
	}

	@Override
	public void saveUnderunitdep(Underunitdep underunitdep) {
		underunitDao.save(underunitdep);

	}

	@Override
	public void updateUnderunitdep(Underunitdep underunitdep) {
		underunitDao.edit(underunitdep);

	}

	@Override
	public void deleteUnderunitdepById(int id) {
		underunitDao.delete(id);

	}

	@Override
	public List<Underunitdep> findAllUnderunitdeps() {
		return underunitDao.findAllUnderUnits();
	}

	@Override
	public boolean isUnderunitdepNameUnique(String callname) {
		Underunitdep underunit=underunitDao.findByCallName(callname);
		return underunit==null;
	}

	@Override
	public List<Underunitdep> findAllUnderUnits(Integer offset, Integer maxResult) {
		return underunitDao.findAllUnderUnits(offset, maxResult);
	}

	@Override
	public List<Underunitdep> findAllUnderUnitsWhereNameLike(String namelike) {
		List<Underunitdep> underunitlist=findAllUnderunitdeps()
				.stream().filter(u -> StringUtil.containsIgnoreCase(u.getCallname(), namelike.trim()))
				.collect(Collectors.toList());
		return underunitlist;
	}

}
