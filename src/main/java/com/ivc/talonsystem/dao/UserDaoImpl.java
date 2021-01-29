package com.ivc.talonsystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);


	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<User> users = (List<User>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers(Integer offset, Integer maxResult) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.setFirstResult(offset != null ? offset : 0);
		criteria.setMaxResults(maxResult != null ? maxResult : 5);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers(String name, String surname, String nickname, AbstractCompany workat) {
		Criteria criteria = createEntityCriteria();
		if (!name.isEmpty()) {
			criteria.add(Restrictions.ilike("firstName", name, MatchMode.ANYWHERE));
		}
		if (!surname.isEmpty()) {
			criteria.add(Restrictions.ilike("lastName", surname, MatchMode.ANYWHERE));
		}
		if (!nickname.isEmpty()) {
			criteria.add(Restrictions.ilike("ssoId", nickname, MatchMode.ANYWHERE));
		}
		if (workat!=null) {
			criteria.add(Restrictions.eq("company", workat));
		}
		List<User> users = (List<User>) criteria.list();
		return users;
	}
}
