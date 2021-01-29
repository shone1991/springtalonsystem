package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.Rju;

public interface RjuService {
	Rju findById(int id);

	Rju findByCallName(String callname);

	void saveRju(Rju rju);

	void updateRju(Rju rju);

	void deleteRjuByCallname(String callname);

	List<Rju> findAllRjus();
	
	boolean isRjuNameUnique(String callname);
}
