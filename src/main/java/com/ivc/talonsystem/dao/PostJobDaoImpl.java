package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.PostJob;

@Repository("postjobDao")
public class PostJobDaoImpl extends AbstractDao<Integer, PostJob> implements PostJobDao {
	
	static final Logger logger = LoggerFactory.getLogger(PostJobDaoImpl.class);

	@Override
	public PostJob findById(int id) {
		PostJob job=getByKey(id);
		return job;
	}

	@Override
	public PostJob findByPostName(String postname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("postname", postname));
        PostJob job = (PostJob)crit.uniqueResult();
        return job;
	}

	@Override
	public void save(PostJob postjob) {
		persist(postjob);

	}

	@Override
	public void edit(PostJob postjob) {
		update(postjob);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostJob> findAllPostJobs() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("postname"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<PostJob> jobs = (List<PostJob>) criteria.list();
        return jobs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostJob> findAllPostJobs(Integer offset, Integer maxResult) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("postname"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.setFirstResult(offset!=null?offset:0);
        criteria.setMaxResults(maxResult!=null?maxResult:5);
        List<PostJob> jobs = (List<PostJob>) criteria.list();
        return jobs;
	}

	@Override
	public void delete(String postname) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("postname", postname));
        PostJob job = (PostJob)crit.uniqueResult();
        delete(job);
		
	}

}
