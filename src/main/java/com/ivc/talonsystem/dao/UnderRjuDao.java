package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.Underrju;

public interface UnderRjuDao {
	Underrju findById(int id);
	
	Underrju findByCallName(String callname);

	void save(Underrju rju);
	
	void edit(Underrju rju);

	void delete(int id);

	List<Underrju> findAllUnderRjus();
	
	List<Underrju> findAllUnderRjus(Integer offset, Integer maxResult);
}
