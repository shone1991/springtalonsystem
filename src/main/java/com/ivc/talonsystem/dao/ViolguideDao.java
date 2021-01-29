package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.Violguide;

public interface ViolguideDao {
	Violguide findById(int id);

	Violguide findBycontentViol(String contentViol);

	void save(Violguide violguide);

	void edit(Violguide violguide);

	void delete(int id);

	List<Violguide> findAllViolguides();
	
	List<Violguide> findAllViolguides(Integer offset, Integer maxResult);
}
