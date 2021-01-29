package com.ivc.talonsystem.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.ConclusionViolGuide;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.Violguide;
import com.ivc.talonsystem.service.AbstractCompanyService;
import com.ivc.talonsystem.service.ConclusionViolGuideService;
import com.ivc.talonsystem.service.PostJobService;
import com.ivc.talonsystem.service.RjuService;
import com.ivc.talonsystem.service.UnitDepartService;
import com.ivc.talonsystem.service.UserService;
import com.ivc.talonsystem.service.ViolationService;
import com.ivc.talonsystem.service.ViolguideService;
import com.ivc.talonsystem.util.BuildString;
import com.ivc.talonsystem.util.DateFormatter;

@Controller
public class ReportController {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ViolationService violationService;

	@Autowired
	AbstractCompanyService abstractCompanyService;

	@Autowired
	UserService userService;

	@Autowired
	PostJobService postjobService;

	@Autowired
	ViolguideService violguideService;

	@Autowired
	ConclusionViolGuideService cncvgdeService;
	
	@Autowired
	UnitDepartService unitDepartService;
	
	@Autowired
	RjuService rjuService;

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	@RequestMapping(value = { "/report/listunit" }, method = RequestMethod.GET)
	public String totalReportGenerateForUnits(@RequestParam(value = "dateseize1", required = false) Date dateseize1, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, 
			@RequestParam(value = "createpdf", required = false) String createpdf,
			ModelMap model, HttpServletRequest request) {
		if(dateseize1==null&&dateseize2==null) {
			LocalDate localDate = LocalDate.now();
			System.out.println("TODAY IS------------"+localDate);
			dateseize1=new GregorianCalendar(localDate.getYear(), Calendar.JANUARY, 1).getTime();
			dateseize2=new GregorianCalendar(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth()).getTime();
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}else if(dateseize1!=null&&dateseize2==null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize1)}));
		}else if(dateseize1==null&&dateseize2!=null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize2)}));
		}else {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}
		List<UnitDepart> companylist=unitDepartService.findAllUnitDeparts();
		List<ConclusionViolGuide> concvgdlist=cncvgdeService.findAllConclusionViolGuides();
		Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultmap=violationService.generateReportForUnit(companylist, concvgdlist, dateseize1, dateseize2);
		Map<AbstractCompany, Integer> grandtotal=violationService.getGrandTotalUnitDepart(resultmap, companylist, concvgdlist);
		model.addAttribute("report", resultmap);
		model.addAttribute("grandtotal", grandtotal);
		model.addAttribute("companies", companylist);
		model.addAttribute("title", "Итоговый отчет по службам");
		model.addAttribute("amandatitle", "Итоговый отчет по службам");
		if(createpdf==null) {
			return "report/reportlistforunit";
		}else {
			return "pdfReportViewForUnit";
		}
		
	}
	
	@RequestMapping(value = { "/report/listrju" }, method = RequestMethod.GET)
	public String totalReportGenerateForRju(@RequestParam(value = "dateseize1", required = false) Date dateseize1, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2,
			@RequestParam(value = "createpdf", required = false) String createpdf, ModelMap model, HttpServletRequest request) {
		if(dateseize1==null&&dateseize2==null) {
			LocalDate localDate = LocalDate.now();
			System.out.println("TODAY IS------------"+localDate);
			dateseize1=new GregorianCalendar(localDate.getYear(), Calendar.JANUARY, 1).getTime();
			dateseize2=new GregorianCalendar(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth()).getTime();
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}else if(dateseize1!=null&&dateseize2==null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize1)}));
		}else if(dateseize1==null&&dateseize2!=null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize2)}));
		}else {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}
		List<Rju> companylist=rjuService.findAllRjus();
		List<ConclusionViolGuide> concvgdlist=cncvgdeService.findAllConclusionViolGuides();
		Map<ConclusionViolGuide, Map<AbstractCompany, Integer>> resultmap=violationService.generateReportForRju(companylist, concvgdlist, dateseize1, dateseize2);
		Map<AbstractCompany, Integer> grandtotal=violationService.getGrandTotalRju(resultmap, companylist, concvgdlist);
		model.addAttribute("report", resultmap);
		model.addAttribute("grandtotal", grandtotal);
		model.addAttribute("companies", companylist);
		model.addAttribute("title", "Итоговый отчет по РЖУ");
		model.addAttribute("amandatitle", "Итоговый отчет по РЖУ");
		if(createpdf==null) {
			return "report/reportlistforrju";
		}else {
			return "pdfReportViewForRju";
		}
		
	}
	
	@RequestMapping(value = { "/report/listunitdetail" }, method = RequestMethod.GET)
	public String detailReportGenerateForUnits(@RequestParam(value = "dateseize1", required = false) Date dateseize1, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, 
			@RequestParam(value = "createpdf", required = false) String createpdf,
			ModelMap model, HttpServletRequest request) {
		if(dateseize1==null&&dateseize2==null) {
			LocalDate localDate = LocalDate.now();
			System.out.println("TODAY IS------------"+localDate);
			dateseize1=new GregorianCalendar(localDate.getYear(), Calendar.JANUARY, 1).getTime();
			dateseize2=new GregorianCalendar(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth()).getTime();
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}else if(dateseize1!=null&&dateseize2==null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize1)}));
		}else if(dateseize1==null&&dateseize2!=null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize2)}));
		}else {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}
		List<UnitDepart> companylist=unitDepartService.findAllUnitDeparts();
		List<Violguide> guidelist=violguideService.findAllViolguides();
		Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap=violationService.generateDetailReportForUnit(companylist, guidelist, dateseize1, dateseize2);
		Map<Integer, Map<AbstractCompany, Integer>> grandtotal=violationService.getDetailedGrandTotalForUnit(resultmap, companylist, guidelist);
		model.addAttribute("report", resultmap);
		model.addAttribute("grandtotal", grandtotal);
		model.addAttribute("companies", companylist);
		model.addAttribute("title", "Отчет по службам");
		model.addAttribute("amandatitle", "Отчет по службам");
		if(createpdf==null) {
			return "report/detailedreportlistforunit";
		}else {
			return "pdfReportViewForUnitDetail";//should create another view
		}
		
	}
	
	@RequestMapping(value = { "/report/listrjudetail" }, method = RequestMethod.GET)
	public String detailReportGenerateForRjus(@RequestParam(value = "dateseize1", required = false) Date dateseize1, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, 
			@RequestParam(value = "createpdf", required = false) String createpdf,
			ModelMap model, HttpServletRequest request) {
		if(dateseize1==null&&dateseize2==null) {
			LocalDate localDate = LocalDate.now();
			System.out.println("TODAY IS------------"+localDate);
			dateseize1=new GregorianCalendar(localDate.getYear(), Calendar.JANUARY, 1).getTime();
			dateseize2=new GregorianCalendar(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth()).getTime();
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}else if(dateseize1!=null&&dateseize2==null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize1)}));
		}else if(dateseize1==null&&dateseize2!=null) {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"на",DateFormatter.format(dateseize2)}));
		}else {
			model.addAttribute("datecriteria", BuildString.buildstring(new String[] {"с",DateFormatter.format(dateseize1),
					"по", DateFormatter.format(dateseize2)}));
		}
		List<Rju> companylist=rjuService.findAllRjus();
		List<Violguide> guidelist=violguideService.findAllViolguides();
		Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap=violationService.generateDetailReportForRju(companylist, guidelist, dateseize1, dateseize2);
		Map<Integer, Map<AbstractCompany, Integer>> grandtotal=violationService.getDetailedGrandTotalForRju(resultmap, companylist, guidelist);
		model.addAttribute("report", resultmap);
		model.addAttribute("grandtotal", grandtotal);
		model.addAttribute("companies", companylist);
		model.addAttribute("title", "Отчет по службам");
		model.addAttribute("amandatitle", "Отчет по РЖУ");
		if(createpdf==null) {
			return "report/detailedreportlistforrju";
		}else {
			return "pdfReportViewForRjuDetail";//should create another view
		}
		
	}
}
