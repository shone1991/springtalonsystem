package com.ivc.talonsystem.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.UnitDepartDao;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.util.StringUtil;

@Service("unitdepartService")
@Transactional
public class UnitDepartServiceImpl implements UnitDepartService {

	@Autowired
	private UnitDepartDao unitdepartdao;
	
	@Override
	public UnitDepart findById(int id) {
		return unitdepartdao.findById(id);
	}

	@Override
	public UnitDepart findByCallName(String callname) {
		return unitdepartdao.findByCallName(callname);
	}

	@Override
	public void saveUnitDepart(UnitDepart unit) {
		unitdepartdao.save(unit);

	}

	@Override
	public void updateUnitDepart(UnitDepart unit) {
		unitdepartdao.edit(unit);

	}

	@Override
	public void deleteUnitDepartById(int id) {
		unitdepartdao.delete(id);

	}

	@Override
	public List<UnitDepart> findAllUnitDeparts() {
		return unitdepartdao.findAllUnits();
	}

	@Override
	public boolean isUnitDepartNameUnique(String callname) {
		UnitDepart unit=unitdepartdao.findByCallName(callname);
		return unit==null;
	}

	@Override
	public List<UnitDepart> findAllUnits(Integer offset, Integer maxResult) {
		return unitdepartdao.findAllUnits(offset, maxResult);
	}

	@Override
	public List<UnitDepart> findAllUnitsWhereNameLike(String namelike) {
		List<UnitDepart> unitlist=findAllUnitDeparts();
		Stream<UnitDepart> stream=unitlist.stream().filter(
				u -> StringUtil.containsIgnoreCase(u.getCallname(), namelike.trim()));
		List<UnitDepart> list=stream.collect(Collectors.toList());
		return list;
	}

}
