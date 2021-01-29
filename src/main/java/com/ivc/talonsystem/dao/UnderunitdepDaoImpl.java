package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.Underunitdep;

@Repository("underunitdepDao")
public class UnderunitdepDaoImpl extends AbstractDao<Integer, Underunitdep> implements UnderunitdepDao {

	static final Logger logger = LoggerFactory.getLogger(UnderunitdepDaoImpl.class);
	
	@Override
	public Underunitdep findById(int id) {
		Underunitdep underunit = getByKey(id);
        return underunit;
	}

	@Override
	public Underunitdep findByCallName(String callname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        Underunitdep underunitdep = (Underunitdep)crit.uniqueResult();
        return underunitdep;
	}

	@Override
	public void save(Underunitdep underunit) {
		persist(underunit);

	}
	
	@Override
	public void edit(Underunitdep underunit) {
		update(underunit);

	}

	@Override
	public void delete(int id) {
		delete(findById(id));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Underunitdep> findAllUnderUnits() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Underunitdep> underunitdeps = (List<Underunitdep>) criteria.list();
        return underunitdeps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Underunitdep> findAllUnderUnits(Integer offset, Integer maxResult) {
		Session session=createSession();
		Query q=session.createQuery("SELECT u FROM Underunitdep u ORDER BY u.id ASC");
        q.setFirstResult(offset!=null?offset:0);
        q.setMaxResults(maxResult!=null?maxResult:5);
        List<Underunitdep> underunitdeparts = (List<Underunitdep>) q.list();
        return underunitdeparts;
	}

}
