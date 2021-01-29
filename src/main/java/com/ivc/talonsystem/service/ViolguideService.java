package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.Violguide;

public interface ViolguideService {
	Violguide findById(int id);

	Violguide findByContentViol(String contentViol);

	void saveViolguide(Violguide violguide);

	void updateViolguide(Violguide violguide);

	void deleteViolguideById(int id);

	List<Violguide> findAllViolguides();
	
	List<Violguide> findAllViolguides(Integer offset, Integer maxResult);
	
	boolean isViolguideContentUnique(String contentViol);
}
