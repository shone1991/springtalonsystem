package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.ConclusionViolGuide;


public interface ConclusionViolGuideDao {
	ConclusionViolGuide findById(int id);

	ConclusionViolGuide findByContent(String content);

	void save(ConclusionViolGuide conclviol);

	void edit(ConclusionViolGuide conclviol);

	void delete(int id);

	List<ConclusionViolGuide> findAllConclusionViolGuides();
	
	List<ConclusionViolGuide> findAllConclusionViolGuides(Integer offset, Integer maxResult);
}
