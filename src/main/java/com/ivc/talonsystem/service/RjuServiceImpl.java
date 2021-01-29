package com.ivc.talonsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.RjuDao;
import com.ivc.talonsystem.entity.Rju;

@Service("rjuService")
@Transactional
public class RjuServiceImpl implements RjuService {

	@Autowired
	private RjuDao rjuDao;
	
	@Override
	public Rju findById(int id) {
		return rjuDao.findById(id);
	}

	@Override
	public Rju findByCallName(String callname) {
		return rjuDao.findByCallname(callname);
	}

	@Override
	public void saveRju(Rju rju) {
		rjuDao.save(rju);

	}

	@Override
	public void updateRju(Rju rju) {
		rjuDao.edit(rju);

	}

	@Override
	public void deleteRjuByCallname(String callname) {
		rjuDao.delete(rjuDao.findByCallname(callname));

	}

	@Override
	public List<Rju> findAllRjus() {
		return rjuDao.findAllRjus();
	}

	@Override
	public boolean isRjuNameUnique(String callname) {
		Rju rju=rjuDao.findByCallname(callname);
		return rju==null;
	}

}
