package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.ConclusionViolGuide;

@Repository("conclusionViolGuideDao")
public class ConclusionViolGuideDaoImpl extends AbstractDao<Integer, ConclusionViolGuide>
		implements ConclusionViolGuideDao {
	
	static final Logger logger = LoggerFactory.getLogger(ConclusionViolGuideDaoImpl.class);

	@Override
	public ConclusionViolGuide findById(int id) {
		ConclusionViolGuide conclusionViolGuide = getByKey(id);
        return conclusionViolGuide;
	}

	@Override
	public ConclusionViolGuide findByContent(String content) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("content", content));
        ConclusionViolGuide conclusionViolGuide = (ConclusionViolGuide)crit.uniqueResult();
        return conclusionViolGuide;
	}

	@Override
	public void save(ConclusionViolGuide conclviol) {
		persist(conclviol);

	}

	@Override
	public void edit(ConclusionViolGuide conclviol) {
		update(conclviol);

	}

	@Override
	public void delete(int id) {
		delete(findById(id));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConclusionViolGuide> findAllConclusionViolGuides() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ConclusionViolGuide> conclusionViolGuides = (List<ConclusionViolGuide>) criteria.list();
        return conclusionViolGuides;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConclusionViolGuide> findAllConclusionViolGuides(Integer offset, Integer maxResult) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.setFirstResult(offset!=null?offset:0);
        criteria.setMaxResults(maxResult!=null?maxResult:5);
        List<ConclusionViolGuide> conclusionViolGuides = (List<ConclusionViolGuide>) criteria.list();
        return conclusionViolGuides;
	}

}
