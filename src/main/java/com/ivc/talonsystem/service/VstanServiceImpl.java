package com.ivc.talonsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.VstanDao;
import com.ivc.talonsystem.entity.Vstan;

@Service("vstanService")
@Transactional
public class VstanServiceImpl implements VstanService {

	@Autowired
	private VstanDao vstanDao;
	
	@Override
	public Vstan findById(int id) {
		return vstanDao.findById(id);
	}

	@Override
	public Vstan findByCallName(String callname) {
		return vstanDao.findByCallName(callname);
	}

	@Override
	public void saveVstan(Vstan vstan) {
		vstanDao.save(vstan);

	}

	@Override
	public void updateVstan(Vstan vstan) {
		vstanDao.edit(vstan);

	}

	@Override
	public void deleteVstanByCallname(String callname) {
		vstanDao.delete(findByCallName(callname));

	}

	@Override
	public List<Vstan> findAllVstans() {
		return vstanDao.findAllStations();
	}

}
