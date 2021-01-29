package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.UnitDepart;

public interface UnitDepartDao {
	UnitDepart findById(int id);
	
	UnitDepart findByCallName(String callname);

	void save(UnitDepart unit);
	
	void edit(UnitDepart unit);

	void delete(int id);

	List<UnitDepart> findAllUnits();
	
	List<UnitDepart> findAllUnits(Integer offset, Integer maxResult);
	
}
