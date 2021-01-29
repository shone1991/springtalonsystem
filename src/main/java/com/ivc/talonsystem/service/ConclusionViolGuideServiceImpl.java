package com.ivc.talonsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.ConclusionViolGuideDao;
import com.ivc.talonsystem.entity.ConclusionViolGuide;

@Service("conclusionViolGuideService")
@Transactional
public class ConclusionViolGuideServiceImpl implements ConclusionViolGuideService {
	
	@Autowired
	private ConclusionViolGuideDao dao;

	@Override
	public ConclusionViolGuide findById(int id) {
		return dao.findById(id);
	}

	@Override
	public ConclusionViolGuide findByContent(String content) {
		return dao.findByContent(content);
	}

	@Override
	public void save(ConclusionViolGuide conclviol) {
		dao.save(conclviol);

	}

	@Override
	public void edit(ConclusionViolGuide conclviol) {
		dao.edit(conclviol);

	}

	@Override
	public void delete(int id) {
		dao.delete(id);

	}

	@Override
	public List<ConclusionViolGuide> findAllConclusionViolGuides() {
		return dao.findAllConclusionViolGuides();
	}

	@Override
	public List<ConclusionViolGuide> findAllConclusionViolGuides(Integer offset, Integer maxResult) {
		return dao.findAllConclusionViolGuides(offset, maxResult);
	}

	@Override
	public boolean isContentUnique(String content) {
		ConclusionViolGuide conclusionViolGuide=dao.findByContent(content);
		return conclusionViolGuide==null;
	}

}
