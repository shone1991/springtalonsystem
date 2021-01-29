package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.Underunitdep;

public interface UnderunitdepDao {
	
	Underunitdep findById(int id);
	
	Underunitdep findByCallName(String callname);

	void save(Underunitdep underunit);
	
	void edit(Underunitdep underunit);

	void delete(int id);

	List<Underunitdep> findAllUnderUnits();
	
	List<Underunitdep> findAllUnderUnits(Integer offset, Integer maxResult);

}
