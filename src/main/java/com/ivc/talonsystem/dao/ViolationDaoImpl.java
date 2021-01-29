package com.ivc.talonsystem.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.ConclusionViolGuide;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.entity.Violguide;
import com.ivc.talonsystem.entity.Vstan;

@Repository("violationDao")
public class ViolationDaoImpl extends AbstractDao<Integer, Violation> implements ViolationDao {

	static final Logger logger = LoggerFactory.getLogger(ViolationDaoImpl.class);

	@Override
	public Violation findById(int id) {
		Session session=createSession();
		Query q=session.createQuery("SELECT v FROM Violation v WHERE id=:id ORDER BY v.dateseize ASC");
		q.setParameter("id", id);
		Violation violation=null;
		try {
			violation=(Violation)q.list().iterator().next();
		} catch (Exception e) {
		}
		return violation;
	}

	@Override
	public void save(Violation violation) {
		persist(violation);

	}

	@Override
	public void edit(Violation violation) {
		update(violation);

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Violation> findAllViolations() {
		Session session=createSession();
		Query q=session.createQuery("SELECT v FROM Violation v ORDER BY v.dateseize ASC");
		List<Violation> violations=(List<Violation>)q.list();
		return violations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Violation> findAllViolations(AbstractCompany company, Integer offset, Integer maxResult) {
		Query q=null;
		Session session=createSession();
		if (company instanceof Rju) {
			Rju rju=(Rju)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company in (SELECT s FROM Vstan s WHERE otd=:rju)"
					+ "OR company in (SELECT u FROM Underrju u WHERE rju=:rju)"
					+"OR company=:rju ORDER BY v.dateseize ASC");
			q.setParameter("rju", rju);
			
		}else if (company instanceof UnitDepart) {
			UnitDepart unit=(UnitDepart)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company in (SELECT u FROM Underunitdep u WHERE unitdep=:unit)"
					+ "OR company=:unit ORDER BY v.dateseize ASC");
			q.setParameter("unit", unit);
		}else if (company instanceof Underunitdep) {
			Underunitdep underunit=(Underunitdep)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:underunit ORDER BY v.dateseize ASC");
			q.setParameter("underunit", underunit);
		} else if (company instanceof Underrju) {
			Underrju underrju=(Underrju)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:underrju ORDER BY v.dateseize ASC");
			q.setParameter("underrju", underrju);
		}else if (company instanceof Vstan) {
			Vstan vstan=(Vstan)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:vstan ORDER BY v.dateseize ASC");
			q.setParameter("vstan", vstan);
		}else {
			q=session.createQuery("SELECT v FROM Violation v ORDER BY v.dateseize ASC");
		}
		q.setFirstResult(offset!=null?offset:0);
		q.setMaxResults(maxResult!=null?maxResult:5);
		return (List<Violation>)q.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Violation> findAllViolations(AbstractCompany company) {
		Query q=null;
		Session session=createSession();
		if (company instanceof Rju) {
			Rju rju=(Rju)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company in (SELECT s FROM Vstan s WHERE otd=:rju)"
					+ "OR company in (SELECT u FROM Underrju u WHERE rju=:rju)"
					+"OR company=:rju ORDER BY v.dateseize ASC");
			q.setParameter("rju", rju);
			
		}else if (company instanceof UnitDepart) {
			UnitDepart unit=(UnitDepart)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company in (SELECT u FROM Underunitdep u WHERE unitdep=:unit)"
					+ "OR company=:unit ORDER BY v.dateseize ASC");
			q.setParameter("unit", unit);
		}else if (company instanceof Underunitdep) {
			Underunitdep underunit=(Underunitdep)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:underunit ORDER BY v.dateseize ASC");
			q.setParameter("underunit", underunit);
		} else if (company instanceof Underrju) {
			Underrju underrju=(Underrju)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:underrju ORDER BY v.dateseize ASC");
			q.setParameter("underrju", underrju);
		}else if (company instanceof Vstan) {
			Vstan vstan=(Vstan)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:vstan ORDER BY v.dateseize ASC");
			q.setParameter("vstan", vstan);
		}else {
			q=session.createQuery("SELECT v FROM Violation v ORDER BY v.dateseize ASC");
		}
		return (List<Violation>)q.list();
	}

	@Override
	public void delete(int id) {
        Violation violation = findById(id);
        delete(violation);
		
	}
	@SuppressWarnings({ "unchecked", "deprecation"})
	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, String surname, Integer stubn, Date dateseize1, Date dateseize2){
		Session session=createSession();
		Criteria criteria=session.createCriteria(Violation.class);
		criteria.createAlias("company","company").add(Restrictions.in("company", listcompany));
		criteria.createAlias("post","post", Criteria.LEFT_JOIN);
		if (!surname.isEmpty()) {
			criteria.add(Restrictions.ilike("surnameEmpl", surname, MatchMode.ANYWHERE));
		}
		if (stubn!=null) {
			criteria.add(Restrictions.eq("stubnum", stubn));
		}
		if(dateseize1!=null&&dateseize2==null){
			criteria.add(Restrictions.eq("dateseize", dateseize1));
		} else if(dateseize1==null&&dateseize2!=null){
			criteria.add(Restrictions.eq("dateseize", dateseize2));
		} else if(dateseize1!=null&&dateseize2!=null){
			criteria.add(Restrictions.between("dateseize", dateseize1, dateseize2));
		}
		criteria.addOrder(Order.asc("dateseize"));

		List<Violation> violations = criteria.list();
		return violations;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany,  Integer stubn,
			Date dateseize1, Date dateseize2) {
		Session session=createSession();
		Criteria criteria=session.createCriteria(Violation.class);

		criteria.createAlias("company","company").add(Restrictions.in("company", listcompany));
		criteria.createAlias("post","post", Criteria.LEFT_JOIN);
		if (stubn!=null) {
			criteria.add(Restrictions.eq("stubnum", stubn));
		}
		if(dateseize1!=null&&dateseize2==null){
			criteria.add(Restrictions.eq("dateseize", dateseize1));
		} else if(dateseize1==null&&dateseize2!=null){
			criteria.add(Restrictions.eq("dateseize", dateseize2));
		} else if(dateseize1!=null&&dateseize2!=null){
			criteria.add(Restrictions.between("dateseize", dateseize1, dateseize2));
		}
		criteria.addOrder(Order.asc("dateseize"));
		List<Violation> violations = criteria.list();
		return violations;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2) {
		Session session=createSession();
		Criteria criteria=session.createCriteria(Violation.class);

		criteria.createAlias("company","company").add(Restrictions.in("company", listcompany));
		criteria.createAlias("post","post", Criteria.LEFT_JOIN);
		if(dateseize1!=null&&dateseize2==null){
			criteria.add(Restrictions.eq("dateseize", dateseize1));
		} else if(dateseize1==null&&dateseize2!=null){
			criteria.add(Restrictions.eq("dateseize", dateseize2));
		} else if(dateseize1!=null&&dateseize2!=null){
			criteria.add(Restrictions.between("dateseize", dateseize1, dateseize2));
		}
		criteria.addOrder(Order.asc("dateseize"));
		List<Violation> violations = criteria.list();
		return violations;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2,
			ConclusionViolGuide conc) {
		Session session=createSession();
		Criteria criteria=session.createCriteria(Violation.class);

		criteria.createAlias("company","company").add(Restrictions.in("company", listcompany));
		criteria.createAlias("post","post", Criteria.LEFT_JOIN);
//		criteria.createAlias("violguide", "violguide").add(Restrictions.eqOrIsNull("violguide.conclviolguide", conc));
		criteria.createAlias("violguide", "violguide", Criteria.LEFT_JOIN).add(Restrictions.eq("violguide.conclviolguide", conc));
		if(dateseize1!=null&&dateseize2==null){
			criteria.add(Restrictions.eq("dateseize", dateseize1));
		} else if(dateseize1==null&&dateseize2!=null){
			criteria.add(Restrictions.eq("dateseize", dateseize2));
		} else if(dateseize1!=null&&dateseize2!=null){
			criteria.add(Restrictions.between("dateseize", dateseize1, dateseize2));
		}
		criteria.addOrder(Order.asc("dateseize"));
		List<Violation> violations = criteria.list();
		return violations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Violation> findAllViolations(AbstractCompany company, Date d1, Date d2, Integer offset,
			Integer maxResult) {
		Query q=null;
		Session session=createSession();
		if (company instanceof Rju) {
			Rju rju=(Rju)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company in (SELECT s FROM Vstan s WHERE otd=:rju)"
					+ "OR company in (SELECT u FROM Underrju u WHERE rju=:rju)"
					+"OR company=:rju AND dateseize BETWEEN :d1 and :d2 ORDER BY v.dateseize ASC");
			q.setParameter("rju", rju);
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
			
		}else if (company instanceof UnitDepart) {
			UnitDepart unit=(UnitDepart)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company in (SELECT u FROM Underunitdep u WHERE unitdep=:unit)"
					+ "OR company=:unit AND dateseize BETWEEN :d1 and :d2 ORDER BY v.dateseize ASC");
			q.setParameter("unit", unit);
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
		}else if (company instanceof Underunitdep) {
			Underunitdep underunit=(Underunitdep)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:underunit AND dateseize BETWEEN :d1 and :d2 ORDER BY v.dateseize ASC");
			q.setParameter("underunit", underunit);
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
		} else if (company instanceof Underrju) {
			Underrju underrju=(Underrju)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:underrju AND dateseize BETWEEN :d1 and :d2 ORDER BY v.dateseize ASC");
			q.setParameter("underrju", underrju);
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
		}else if (company instanceof Vstan) {
			Vstan vstan=(Vstan)company;
			q=session.createQuery("SELECT v FROM Violation v WHERE company=:vstan AND dateseize BETWEEN :d1 and :d2 ORDER BY v.dateseize ASC");
			q.setParameter("vstan", vstan);
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
		}else {
			q=session.createQuery("SELECT v FROM Violation v WHERE dateseize BETWEEN :d1 and :d2 ORDER BY v.dateseize ASC");
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
		}
		q.setFirstResult(offset!=null?offset:0);
		q.setMaxResults(maxResult!=null?maxResult:5);
		return (List<Violation>)q.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2,
			Violguide violguide, int stubnumber) {
		Session session=createSession();
		Criteria criteria=session.createCriteria(Violation.class);

		criteria.createAlias("company","company").add(Restrictions.in("company", listcompany));
		criteria.createAlias("post","post", Criteria.LEFT_JOIN);
		criteria.createAlias("violguide", "violguide", Criteria.LEFT_JOIN).add(Restrictions.eq("violguide", violguide));
		criteria.add(Restrictions.eq("stubnum", stubnumber));
		if(dateseize1!=null&&dateseize2==null){
			criteria.add(Restrictions.eq("dateseize", dateseize1));
		} else if(dateseize1==null&&dateseize2!=null){
			criteria.add(Restrictions.eq("dateseize", dateseize2));
		} else if(dateseize1!=null&&dateseize2!=null){
			criteria.add(Restrictions.between("dateseize", dateseize1, dateseize2));
		}
		criteria.addOrder(Order.asc("dateseize"));
		List<Violation> violations = criteria.list();
		return violations;
	}

}
