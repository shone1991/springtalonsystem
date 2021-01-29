package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.Rju;

@Repository("rjuDao")
public class RjuDaoImpl extends AbstractDao<Integer, Rju> implements RjuDao{

	static final Logger logger = LoggerFactory.getLogger(RjuDaoImpl.class);
	
	@Override
	public Rju findById(int id) {
		Rju rju = getByKey(id);
        return rju;
	}

	@Override
	public void save(Rju rju) {
		persist(rju);
		
	}
	
	@Override
	public void edit(Rju rju) {
		update(rju);
		
	}

	@Override
	public void delete(Rju rju) {
        delete(rju);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rju> findAllRjus() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("namerju"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Rju> units = (List<Rju>) criteria.list();
        return units;
	}

	@Override
	public Rju findByCallname(String callname) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        Rju unit = (Rju)crit.uniqueResult();
        return unit;
	}

}
