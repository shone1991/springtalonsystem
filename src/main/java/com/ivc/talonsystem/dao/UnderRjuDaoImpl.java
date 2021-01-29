package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.Underrju;

@Repository("underrjuDao")
public class UnderRjuDaoImpl extends AbstractDao<Integer, Underrju> implements UnderRjuDao {

	static final Logger logger = LoggerFactory.getLogger(UnderRjuDaoImpl.class);
	
	@Override
	public Underrju findById(int id) {
		Underrju underrju = getByKey(id);
        return underrju;
	}

	@Override
	public Underrju findByCallName(String callname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        Underrju underrju = (Underrju)crit.uniqueResult();
        return underrju;
	}

	@Override
	public void save(Underrju underrju) {
		persist(underrju);

	}
	
	@Override
	public void edit(Underrju underrju) {
		update(underrju);

	}

	@Override
	public void delete(int id) {
		delete(findById(id));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Underrju> findAllUnderRjus() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Underrju> underRjus = (List<Underrju>) criteria.list();
        return underRjus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Underrju> findAllUnderRjus(Integer offset, Integer maxResult) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("callname"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.setFirstResult(offset!=null?offset:0);
        criteria.setMaxResults(maxResult!=null?maxResult:5);
        List<Underrju> underrjus = (List<Underrju>) criteria.list();
        return underrjus;
	}

}
