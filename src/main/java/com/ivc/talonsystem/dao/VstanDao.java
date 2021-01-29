package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.Vstan;

public interface VstanDao {
	Vstan findById(int id);
	
	Vstan findByCallName(String callname);

	void save(Vstan stan);
	
	void edit(Vstan stan);

	void delete(Vstan stan);

	List<Vstan> findAllStations();
}
