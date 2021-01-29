package com.ivc.talonsystem.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.ConclusionViolGuide;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.entity.Violguide;

public interface ViolationService {
	Violation findById(int id);

	void saveViolation(Violation violation);

	void updateViolation(Violation violation);

	void deleteViolation(int id);

	List<Violation> findAllViolations(AbstractCompany company, Integer offset, Integer maxResult);
	
	List<Violation> findAllViolations(AbstractCompany company, Date d1, Date d2, Integer offset, Integer maxResult);
	
	List<Violation> findAllViolations(AbstractCompany company);

	List<Violation> findAllViolations(List<AbstractCompany> listcompany, String surname, Integer stubn, Date dateseize1, Date dateseize2);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany,  Integer stubn, Date dateseize1, Date dateseize2);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2, Violguide violguide, int stubnumber);
	
	List<Violation> findAllViolations(List<AbstractCompany> listcompany, Date dateseize1, Date dateseize2, ConclusionViolGuide conc);
	
	Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> generateReport(List<AbstractCompany> companylist, List<ConclusionViolGuide> concvgdlist, Date ds1, Date ds2);
	
	Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> generateReportForUnit(List<UnitDepart> companylist, List<ConclusionViolGuide> concvgdlist, Date ds1, Date ds2);
	
	Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> generateReportForRju(List<Rju> companylist, List<ConclusionViolGuide> concvgdlist, Date ds1, Date ds2);
	
	Map<AbstractCompany, Integer> getGrandTotal(Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultMap,
			List<AbstractCompany> companylist, List<ConclusionViolGuide> concvgdlist);
	
	Map<AbstractCompany, Integer> getGrandTotalUnitDepart(Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultMap,
			List<UnitDepart> companylist, List<ConclusionViolGuide> concvgdlist);
	
	Map<AbstractCompany, Integer> getGrandTotalRju(Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultMap,
			List<Rju> companylist, List<ConclusionViolGuide> concvgdlist);
	
	Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> generateDetailReportForUnit(List<UnitDepart> companylist, List<Violguide> violguidelist, Date ds1, Date ds2);
	
	Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> generateDetailReportForRju(List<Rju> companylist, List<Violguide> violguidelist, Date ds1, Date ds2);
	
	Map<Integer, Map<AbstractCompany, Integer>> getDetailedGrandTotalForUnit(Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap,
			List<UnitDepart> companylist, List<Violguide> listguide);
	
	Map<Integer, Map<AbstractCompany, Integer>> getDetailedGrandTotalForRju(Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap,
			List<Rju> companylist, List<Violguide> listguide);
}
