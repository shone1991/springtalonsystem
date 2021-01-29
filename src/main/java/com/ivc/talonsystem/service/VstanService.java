package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.Vstan;

public interface VstanService {
	Vstan findById(int id);

	Vstan findByCallName(String callname);

	void saveVstan(Vstan vstan);

	void updateVstan(Vstan vstan);

	void deleteVstanByCallname(String callname);

	List<Vstan> findAllVstans();
	
}
