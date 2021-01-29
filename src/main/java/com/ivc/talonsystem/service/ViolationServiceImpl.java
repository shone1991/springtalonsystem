package com.ivc.talonsystem.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.DTO.StubNumber;
import com.ivc.talonsystem.dao.ViolationDao;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.ConclusionViolGuide;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.entity.Violguide;
import com.ivc.talonsystem.util.MapUtility;

@Service("violationService")
@Transactional
public class ViolationServiceImpl implements ViolationService {

	@Autowired
	private ViolationDao violationDao;

	@Autowired
	private AbstractCompanyService abstractCompanyService; 
	
	@Override
	public Violation findById(int id) {
		return violationDao.findById(id);
	}

	@Override
	public void saveViolation(Violation violation) {
		violationDao.save(violation);

	}

	@Override
	public void updateViolation(Violation violation) {
		violationDao.edit(violation);

	}

	@Override
	public List<Violation> findAllViolations(AbstractCompany company, Integer offset, Integer maxResult) {
		return violationDao.findAllViolations(company, offset,  maxResult);
	}

	@Override
	public List<Violation> findAllViolations(AbstractCompany company) {
		return violationDao.findAllViolations(company);
	}

	@Override
	public void deleteViolation(int id) {
		violationDao.delete(id);
		
	}

	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, String surname, Integer stubnum, Date dateseize, Date dateseize2){
		return violationDao.findAllViolations(listcompany, surname,  stubnum, dateseize, dateseize2);
	}

	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Integer stubn,
			Date dateseize1, Date dateseize2) {
		return violationDao.findAllViolations(listcompany,  stubn, dateseize1, dateseize2);
	}

	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2) {
		return violationDao.findAllViolations(listcompany, dateseize1, dateseize2);
	}

	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2,
			ConclusionViolGuide conc) {
		return violationDao.findAllViolations(listcompany, dateseize1, dateseize2, conc);
	}

	@Override
	public Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> generateReport(List<AbstractCompany> companylist,
			List<ConclusionViolGuide> concvgdlist, Date ds1, Date ds2) {
		Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultmap=new HashMap<ConclusionViolGuide, Map<AbstractCompany,Integer>>();
		concvgdlist.forEach(cncvg->{
			HashMap<AbstractCompany, Integer> violcountpercompany = new HashMap<AbstractCompany, Integer>();
			companylist.forEach(comp->{
				int count=findAllViolations(abstractCompanyService.listChildCompanyOfParticularCompany(comp), 
						ds1, ds2, cncvg).size();
				violcountpercompany.put(comp, count);
			});
			resultmap.put(cncvg, violcountpercompany);
		});
		return resultmap;
	}

	@Override
	public Map<AbstractCompany, Integer> getGrandTotal(
			Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultMap, List<AbstractCompany> companylist,
			List<ConclusionViolGuide> concvgdlist) {
		Map<AbstractCompany, Integer> grandtotal=new HashMap<AbstractCompany, Integer>();
		for(AbstractCompany u:companylist) {
			int ugrandtotal=0;
			for(ConclusionViolGuide c: concvgdlist) {
				ugrandtotal+=resultMap.get(c).get(u);
			}
			grandtotal.put(u, ugrandtotal);
		}
		return grandtotal;
	}

	@Override
	public Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> generateReportForUnit(List<UnitDepart> companylist,
			List<ConclusionViolGuide> concvgdlist, Date ds1, Date ds2) {
		Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultmap=new HashMap<ConclusionViolGuide, Map<AbstractCompany,Integer>>();
		concvgdlist.forEach(cncvg->{
			HashMap<AbstractCompany, Integer> violcountpercompany = new HashMap<AbstractCompany, Integer>();
			companylist.forEach(comp->{
				int count=findAllViolations(abstractCompanyService.listChildCompanyOfParticularCompany(comp), 
						ds1, ds2, cncvg).size();
				violcountpercompany.put(comp, count);
			});
			resultmap.put(cncvg, violcountpercompany);
		});
		return resultmap;
	}

	@Override
	public Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> generateReportForRju(List<Rju> companylist,
			List<ConclusionViolGuide> concvgdlist, Date ds1, Date ds2) {
		Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultmap=new HashMap<ConclusionViolGuide, Map<AbstractCompany,Integer>>();
		concvgdlist.forEach(cncvg->{
			HashMap<AbstractCompany, Integer> violcountpercompany = new HashMap<AbstractCompany, Integer>();
			companylist.forEach(comp->{
				int count=findAllViolations(abstractCompanyService.listChildCompanyOfParticularCompany(comp), 
						ds1, ds2, cncvg).size();
				violcountpercompany.put(comp, count);
			});
			resultmap.put(cncvg, violcountpercompany);
		});
		return resultmap;
	}

	@Override
	public Map<AbstractCompany, Integer> getGrandTotalUnitDepart(
			Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultMap, List<UnitDepart> companylist,
			List<ConclusionViolGuide> concvgdlist) {
		Map<AbstractCompany, Integer> grandtotal=new HashMap<AbstractCompany, Integer>();
		for(AbstractCompany u:companylist) {
			int ugrandtotal=0;
			for(ConclusionViolGuide c: concvgdlist) {
				ugrandtotal+=resultMap.get(c).get(u);
			}
			grandtotal.put(u, ugrandtotal);
		}
		return grandtotal;
	}

	@Override
	public Map<AbstractCompany, Integer> getGrandTotalRju(
			Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultMap, List<Rju> companylist,
			List<ConclusionViolGuide> concvgdlist) {
		Map<AbstractCompany, Integer> grandtotal=new HashMap<AbstractCompany, Integer>();
		for(AbstractCompany u:companylist) {
			int ugrandtotal=0;
			for(ConclusionViolGuide c: concvgdlist) {
				ugrandtotal+=resultMap.get(c).get(u);
			}
			grandtotal.put(u, ugrandtotal);
		}
		return grandtotal;
	}

	@Override
	public List<Violation> findAllViolations(AbstractCompany company, Date d1, Date d2, Integer offset,
			Integer maxResult) {
		return violationDao.findAllViolations(company, d1, d2, offset, maxResult);
	}

	@Override
	public List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2,
			Violguide violguide, int stubnumber) {
		return violationDao.findAllViolations(listcompany, dateseize1, dateseize2, violguide, stubnumber);
	}

	@Override
	public Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> generateDetailReportForUnit(
			List<UnitDepart> companylist, List<Violguide> violguidelist, Date ds1, Date ds2) {
		Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap=new HashMap<Integer, Map<Violguide,Map<AbstractCompany,Integer>>>();
		StubNumber.getStubnums().forEach(num->{
			Map<Violguide, Map<AbstractCompany, Integer>> perviolguide=new HashMap<Violguide, Map<AbstractCompany,Integer>>();
			violguidelist.forEach(guide->{
				Map<AbstractCompany, Integer> percompany=new HashMap<AbstractCompany, Integer>();
				companylist.forEach(comp->{
					int count=findAllViolations(abstractCompanyService.listChildCompanyOfParticularCompany(comp),
							ds1, ds2, guide, num).size();
					percompany.put(comp, count);
				});
				perviolguide.put(guide, percompany);
			});
			//sort perviolguide here
			
			//sort perviolguide
			resultmap.put(num, MapUtility.sortOnKeys(perviolguide));
//			resultmap.put(num, perviolguide);
		});
		return resultmap;
	}

	@Override
	public Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> generateDetailReportForRju(List<Rju> companylist,
			List<Violguide> violguidelist, Date ds1, Date ds2) {
		
		Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap=new HashMap<Integer, Map<Violguide,Map<AbstractCompany,Integer>>>();
		StubNumber.getStubnums().forEach(num->{
			Map<Violguide, Map<AbstractCompany, Integer>> perviolguide=new HashMap<Violguide, Map<AbstractCompany,Integer>>();
			violguidelist.forEach(guide->{
				Map<AbstractCompany, Integer> percompany=new HashMap<AbstractCompany, Integer>();
				companylist.forEach(comp->{
					int count=findAllViolations(abstractCompanyService.listChildCompanyOfParticularCompany(comp),
							ds1, ds2, guide, num).size();
					percompany.put(comp, count);
				});
				perviolguide.put(guide, percompany);
			});
//			resultmap.put(num, perviolguide);
			resultmap.put(num, MapUtility.sortOnKeys(perviolguide));
		});
		return resultmap;
	}

	@Override
	public Map<Integer, Map<AbstractCompany, Integer>> getDetailedGrandTotalForUnit(
			Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap, List<UnitDepart> companylist,
			List<Violguide> listguide) {
		Map<Integer, Map<AbstractCompany, Integer>> grandtotal=new HashMap<Integer, Map<AbstractCompany, Integer>>();
		StubNumber.getStubnums().forEach(n->{
			Map<AbstractCompany, Integer> percompany=new HashMap<AbstractCompany, Integer>();
			for(AbstractCompany comp:companylist) {
				int ugrandtotal=0;
				for(Violguide v:listguide) {
					ugrandtotal+=resultmap.get(n).get(v).get(comp);
				}
				percompany.put(comp, ugrandtotal);
			}
			grandtotal.put(n, percompany);
		});
		
		return grandtotal;
	}

	@Override
	public Map<Integer, Map<AbstractCompany, Integer>> getDetailedGrandTotalForRju(
			Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap, List<Rju> companylist,
			List<Violguide> listguide) {
		Map<Integer, Map<AbstractCompany, Integer>> grandtotal=new HashMap<Integer, Map<AbstractCompany, Integer>>();
		StubNumber.getStubnums().forEach(n->{
			Map<AbstractCompany, Integer> percompany=new HashMap<AbstractCompany, Integer>();
			for(AbstractCompany comp:companylist) {
				int ugrandtotal=0;
				for(Violguide v:listguide) {
					ugrandtotal+=resultmap.get(n).get(v).get(comp);
				}
				percompany.put(comp, ugrandtotal);
			}
			grandtotal.put(n, percompany);
		});
		
		return grandtotal;
	}

}
