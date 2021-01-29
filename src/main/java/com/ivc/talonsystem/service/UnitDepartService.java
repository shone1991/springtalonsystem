package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.UnitDepart;

public interface UnitDepartService {
	UnitDepart findById(int id);

	UnitDepart findByCallName(String callname);

	void saveUnitDepart(UnitDepart unit);

	void updateUnitDepart(UnitDepart unit);

	void deleteUnitDepartById(int id);

	List<UnitDepart> findAllUnitDeparts();
	
	List<UnitDepart> findAllUnits(Integer offset, Integer maxResult);
	
	boolean isUnitDepartNameUnique(String callname);
	
	List<UnitDepart> findAllUnitsWhereNameLike(String namelike);
}
