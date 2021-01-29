package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.Underrju;


public interface UnderRjuService {
	Underrju findById(int id);

	Underrju findByCallName(String callname);

	void saveUnderrju(Underrju underrju);

	void updateUnderrju(Underrju underrju);

	void deleteUnderrjuById(int id);

	List<Underrju> findAllUnderrjus();
	
	List<Underrju> findAllUnderRjus(Integer offset, Integer maxResult);
	
	boolean isUnderRjuNameUnique(Integer id, String callname);
	
	List<Underrju> findAllUnderRjusWhereNameLike(String namelike);
}
