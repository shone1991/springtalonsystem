package com.ivc.talonsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.ViolguideDao;
import com.ivc.talonsystem.entity.Violguide;

@Service("violguideService")
@Transactional
public class ViolguideServiceImpl implements ViolguideService {

	@Autowired
	private ViolguideDao violguideDao;
	
	@Override
	public Violguide findById(int id) {
		return violguideDao.findById(id);
	}

	@Override
	public Violguide findByContentViol(String contentViol) {
		return violguideDao.findBycontentViol(contentViol);
	}

	@Override
	public void saveViolguide(Violguide violguide) {
		violguideDao.save(violguide);

	}

	@Override
	public void updateViolguide(Violguide violguide) {
		violguideDao.edit(violguide);

	}

	@Override
	public List<Violguide> findAllViolguides() {
		return violguideDao.findAllViolguides();
	}

	@Override
	public boolean isViolguideContentUnique(String contentViol) {
		Violguide violguide=violguideDao.findBycontentViol(contentViol);
		return violguide==null;
	}

	@Override
	public List<Violguide> findAllViolguides(Integer offset, Integer maxResult) {
		return violguideDao.findAllViolguides(offset, maxResult);
	}

	@Override
	public void deleteViolguideById(int id) {
		violguideDao.delete(id);
		
	}


}
