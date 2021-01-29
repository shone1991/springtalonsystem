package com.ivc.talonsystem.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.UnderRjuDao;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.util.StringUtil;


@Service("underrjuService")
@Transactional
public class UnderRjuServiceImpl implements UnderRjuService {

	@Autowired
	private UnderRjuDao underrjuDao;
	
	@Override
	public Underrju findById(int id) {
		return underrjuDao.findById(id);
	}

	@Override
	public Underrju findByCallName(String callname) {
		return underrjuDao.findByCallName(callname);
	}

	@Override
	public void saveUnderrju(Underrju underrju) {
		underrjuDao.save(underrju);

	}

	@Override
	public void updateUnderrju(Underrju underrju) {
		underrjuDao.edit(underrju);

	}

	@Override
	public void deleteUnderrjuById(int id) {
		Underrju underrju=underrjuDao.findById(id);
		Collection<Underrju> underrjulist=underrju.getRju().getUnderRjuCollection();
		if(underrjulist.contains(underrju)) {
			underrjulist.remove(underrju);
		}
		underrjuDao.delete(id);

	}

	@Override
	public List<Underrju> findAllUnderrjus() {
		return underrjuDao.findAllUnderRjus();
	}


	@Override
	public List<Underrju> findAllUnderRjus(Integer offset, Integer maxResult) {
		return underrjuDao.findAllUnderRjus(offset, maxResult);
	}

	@Override
	public boolean isUnderRjuNameUnique(Integer id, String callname) {
		Underrju underrju=underrjuDao.findByCallName(callname);
        return ( underrju == null || ((id != null) && (underrju.getId() == id)));
	}

	@Override
	public List<Underrju> findAllUnderRjusWhereNameLike(String namelike) {
		List<Underrju> underrjulist=findAllUnderrjus().stream()
				.filter(u -> StringUtil.containsIgnoreCase(u.getCallname(), namelike.trim()))
				.collect(Collectors.toList());
		return underrjulist;
	}

}
