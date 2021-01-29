package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.Vstan;

@Repository("vstanDao")
public class VstanDaoImpl extends AbstractDao<Integer, Vstan> implements VstanDao {
	
	static final Logger logger = LoggerFactory.getLogger(VstanDaoImpl.class); 

	@Override
	public Vstan findById(int id) {
		Vstan stan=getByKey(id);
		return stan;
	}

	@Override
	public Vstan findByCallName(String callname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        Vstan stan = (Vstan)crit.uniqueResult();
        return stan;
	}

	@Override
	public void save(Vstan stan) {
		persist(stan);

	}
	
	@Override
	public void edit(Vstan stan) {
		update(stan);

	}

	@Override
	public void delete(Vstan stan) {
		delete(stan);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vstan> findAllStations() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Vstan> stans = (List<Vstan>) criteria.list();
        return stans;
	}

}
