package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.UnitDepart;

@Repository("unitdepDao")
public class UnitDepartDaoImpl extends AbstractDao<Integer, UnitDepart> implements UnitDepartDao {

	static final Logger logger = LoggerFactory.getLogger(UnitDepartDaoImpl.class); 
	
	@Override
	public UnitDepart findById(int id) {
		UnitDepart unit = getByKey(id);
        return unit;
	}

	@Override
	public UnitDepart findByCallName(String callname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        UnitDepart unitdep = (UnitDepart)crit.uniqueResult();
        return unitdep;
	}

	@Override
	public void save(UnitDepart unit) {
		persist(unit);

	}
	
	@Override
	public void edit(UnitDepart unit) {
		update(unit);

	}

	@Override
	public void delete(int id) {
		delete(findById(id));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnitDepart> findAllUnits() {
		Session session=createSession();
		Query q=session.createQuery("SELECT u FROM UnitDepart u ORDER BY u.id ASC");
        List<UnitDepart> unitdeps = (List<UnitDepart>) q.list();
        return unitdeps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnitDepart> findAllUnits(Integer offset, Integer maxResult) {
		Session session=createSession();
		Query q=session.createQuery("SELECT u FROM UnitDepart u ORDER BY u.id ASC");
        q.setFirstResult(offset!=null?offset:0);
        q.setMaxResults(maxResult!=null?maxResult:5);
        List<UnitDepart> unitdeparts = (List<UnitDepart>) q.list();
        return unitdeparts;
	}


}
