package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.Underunitdep;

public interface UnderUnitDepService {
	Underunitdep findById(int id);

	Underunitdep findByCallName(String callname);

	void saveUnderunitdep(Underunitdep underunitdep);

	void updateUnderunitdep(Underunitdep underunitdep);

	void deleteUnderunitdepById(int id);

	List<Underunitdep> findAllUnderunitdeps();
	
	List<Underunitdep> findAllUnderUnits(Integer offset, Integer maxResult);
	
	boolean isUnderunitdepNameUnique(String callname);
	
	List<Underunitdep> findAllUnderUnitsWhereNameLike(String namelike);
}
