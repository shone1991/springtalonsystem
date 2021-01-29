package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.Rju;

public interface RjuDao{
	
	Rju findById(int id);
	
	Rju findByCallname(String callname);

	void save(Rju rju);
	
	void edit(Rju rju);

	void delete(Rju rju);

	List<Rju> findAllRjus();
}
