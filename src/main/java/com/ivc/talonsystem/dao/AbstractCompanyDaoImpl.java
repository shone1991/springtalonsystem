 package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.AbstractCompany;

@Repository("abstractCompanyDao")
public class AbstractCompanyDaoImpl extends AbstractDao<Integer, AbstractCompany> implements AbstractCompanyDao {

	static final Logger logger = LoggerFactory.getLogger(AbstractCompanyDaoImpl.class);
	@Override
	public AbstractCompany findById(int id) {
		AbstractCompany abstractCompany = getByKey(id);
        return abstractCompany;
	}

	@Override
	public AbstractCompany findByCallName(String callname) {
		logger.info("callname : {}", callname);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        AbstractCompany abstractCompany = (AbstractCompany)crit.uniqueResult();
        return abstractCompany;
	}

	@Override
	public void save(AbstractCompany abstractCompany) {
		persist(abstractCompany);
		
	}
	
	@Override
	public void edit(AbstractCompany abstractCompany) {
		update(abstractCompany);
		
	}

	@Override
	public void deleteByCallName(String callname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("callname", callname));
        AbstractCompany abstractCompany = (AbstractCompany)crit.uniqueResult();
        delete(abstractCompany);
		
	}

	@Override
    @SuppressWarnings("unchecked")
	public List<AbstractCompany> findAllAbstractCompanys() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("callname"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<AbstractCompany> abstractCompanys = (List<AbstractCompany>) criteria.list();
        return abstractCompanys;
	}

	@Override
	public void deleteById(int id) {
		delete(findById(id));
		
	}



	

}
