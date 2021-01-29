package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.Violguide;

@Repository("violguideDao")
public class ViolguideDaoImpl extends AbstractDao<Integer, Violguide> implements ViolguideDao {
	
	static final Logger logger = LoggerFactory.getLogger(ViolguideDaoImpl.class);

	@Override
	public Violguide findById(int id) {
		Violguide violguide=getByKey(id);
		return violguide;
	}

	@Override
	public Violguide findBycontentViol(String contentViol) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("contentViol", contentViol));
        Violguide guide = (Violguide)crit.uniqueResult();
        return guide;
	}

	@Override
	public void save(Violguide violguide) {
		persist(violguide);

	}

	@Override
	public void edit(Violguide violguide) {
		update(violguide);

	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Violguide> findAllViolguides() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Violguide> jobs = (List<Violguide>) criteria.list();
        return jobs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Violguide> findAllViolguides(Integer offset, Integer maxResult) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.setFirstResult(offset!=null?offset:0);
        criteria.setMaxResults(maxResult!=null?maxResult:5);
        List<Violguide> violguides = (List<Violguide>) criteria.list();
        return violguides;
	}

	@Override
	public void delete(int id) {
		delete(findById(id));
		
	}

}
