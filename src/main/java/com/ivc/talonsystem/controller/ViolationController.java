package com.ivc.talonsystem.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.PostJob;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.entity.Violguide;
import com.ivc.talonsystem.entity.Vstan;
import com.ivc.talonsystem.service.AbstractCompanyService;
import com.ivc.talonsystem.service.PostJobService;
import com.ivc.talonsystem.service.UserService;
import com.ivc.talonsystem.service.ViolationService;
import com.ivc.talonsystem.service.ViolguideService;

@Controller
@SessionAttributes({ "user" })
public class ViolationController {

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

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = { "/", "/violations/violationlist" }, method = RequestMethod.GET)
	public ModelAndView listViolations(Principal principal, HttpServletRequest request) {
		LocalDate localDate = LocalDate.now();
		Date dateseize1=new GregorianCalendar(localDate.getYear(), Calendar.JANUARY, 1).getTime();
		Date dateseize2=new GregorianCalendar(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()).getTime();
		ModelAndView model = new ModelAndView("violations/violationlist");
		User user = userService.findBySSO(principal.getName());
		request.getSession().setAttribute("currentuser", user);
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
//		List<Violation> violationlist = violationService.findAllViolations(user.getCompany(),
//				(page - 1) * recordsPerPage, recordsPerPage);
		List<Violation> violationlist = violationService.findAllViolations(user.getCompany(), dateseize1, dateseize2,
				(page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = violationService.findAllViolations(user.getCompany()).size(); // do not pull the database
		System.out.println("numrec: " + noOfRecords);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		System.out.println("amountpage: " + noOfPages);
		model.addObject("violationlist", violationlist);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("title", "Талоны");
		model.addObject("amandatitle", "Талоны");
		return model;
	}

	@RequestMapping(value = { "/violations/createviolation" }, method = RequestMethod.GET)
	public String createViolation(Principal principal, ModelMap model, HttpServletRequest request) {
		AbstractCompany comp=((User)request.getSession().getAttribute("currentuser")).getCompany();
		if(comp instanceof Rju || comp instanceof UnitDepart) {
			model.addAttribute("hascompanies", true);
		} else if(comp instanceof Vstan || comp instanceof Underunitdep) {
			model.addAttribute("hascompanies", false);
		} else if(comp instanceof AbstractCompany){
			model.addAttribute("hascompanies", true);
		}
		Violation violation = new Violation();
		model.addAttribute("title", "Талоны");
		model.addAttribute("violation", violation);
		model.addAttribute("jobs", initializeJobs());
		model.addAttribute("violguides", initializeViolguides());
		model.addAttribute("edit", false);
		model.addAttribute("amandatitle", "Талоны");
		model.addAttribute("bodytitle", "Создать запись");
		return "violations/createviolation";
	}

	@RequestMapping(value = { "/violations/createviolation" }, method = RequestMethod.POST)
	public String saveViolation(@Valid Violation violation,  BindingResult result, ModelMap model, Principal principal, HttpServletRequest request) {
		AbstractCompany comp=((User)request.getSession().getAttribute("currentuser")).getCompany();
		System.out.println("class:"+comp.getClass().getSimpleName());
		if(comp instanceof Rju || comp instanceof UnitDepart) {
			System.out.println("comp instanceof AbstractCompany || comp instanceof Rju || comp instanceof UnitDepart");
			model.addAttribute("hascompanies", true);
		} else if(comp instanceof Vstan || comp instanceof Underunitdep) {
			System.out.println("comp instanceof Vstan || comp instanceof Underunitdep");
			model.addAttribute("hascompanies", false);
		} else if(comp instanceof AbstractCompany){
			System.out.println("comp instanceof Vstan || comp instanceof UnderRju ");
			model.addAttribute("hascompanies", true);
		}
		Collection<AbstractCompany> listcompanies=initializeAbstractCompanies(principal.getName());
		model.addAttribute("title", "Талоны");
		model.addAttribute("jobs", initializeJobs());
		model.addAttribute("violguides", initializeViolguides());
		model.addAttribute("edit", false);
		model.addAttribute("amandatitle", "Талоны");
		model.addAttribute("bodytitle", "Создать запись");
		if (result.hasErrors()&&!result.getFieldError().getField().equals("violguide.conclviolguide")) {
			System.out.println("FieldError: "+result.getFieldError());
			System.out.println("FieldErrorField: "+result.getFieldError().getField());
			System.out.println("FieldErrorCode: "+result.getFieldError().getCode());
			System.out.println("FieldErrorDeffaultMessage: "+result.getFieldError().getDefaultMessage());
			return "violations/createviolation";
		}
		AbstractCompany acompany=abstractCompanyService.findByCallName(violation.getCompany().getCallname());
		if(acompany==null || !listcompanies.contains(acompany)) {
			FieldError companyError = new FieldError("violation", "company.callname", messageSource.getMessage("not.exist.company.callname",
					new String[] { violation.getCompany().getCallname() }, Locale.getDefault()));
			result.addError(companyError);
			return "violations/createviolation";
		}
		String postname=violation.getPost().getPostname();
		PostJob job = postjobService.findByPostName(postname);
		if(!postname.isEmpty() && job==null) {
			FieldError postError = new FieldError("violation", "post.postname", messageSource.getMessage("not.exist.post.postname",
					new String[] { violation.getPost().getPostname() }, Locale.getDefault()));
			result.addError(postError);
			return "violations/createviolation";
		}
		Violguide guide = violguideService.findByContentViol(violation.getViolguide().getContentViol());
		if(guide==null) {
			FieldError guideError = new FieldError("violation", "violguide.contentViol", messageSource.getMessage("not.exist.violguide.contentViol",
					new String[] { violation.getViolguide().getContentViol() }, Locale.getDefault()));
			result.addError(guideError);
			return "violations/createviolation";
		}

		violation.setCompany(acompany);
		violation.setPost(job);
		violation.setViolguide(guide);

		violationService.saveViolation(violation);

		return "violations/violsuccess";
	}

	@RequestMapping(value = { "/violations/edit-violation-{id}" }, method = RequestMethod.GET)
	public String editViolation(@PathVariable Integer id,  Principal principal, ModelMap model, HttpServletRequest request) {
		AbstractCompany comp=((User)request.getSession().getAttribute("currentuser")).getCompany();
		System.out.println("class:"+comp.getClass().getSimpleName());
		if(comp instanceof Rju || comp instanceof UnitDepart) {
			System.out.println("comp instanceof AbstractCompany || comp instanceof Rju || comp instanceof UnitDepart");
			model.addAttribute("hascompanies", true);
		} else if(comp instanceof Vstan || comp instanceof Underunitdep) {
			System.out.println("comp instanceof Vstan || comp instanceof Underunitdep");
			model.addAttribute("hascompanies", false);
		} else if(comp instanceof AbstractCompany){
			System.out.println("comp instanceof Vstan || comp instanceof UnderRju ");
			model.addAttribute("hascompanies", true);
		}
		Violation violation = violationService.findById(id);
		model.addAttribute("violation", violation);
		model.addAttribute("title", "Талоны");
		model.addAttribute("amandatitle", "Талоны");
		model.addAttribute("edit", true);
		model.addAttribute("jobs", initializeJobs());
		model.addAttribute("violguides", initializeViolguides());
		return "violations/createviolation";
	}
	
	@RequestMapping(value = { "/violations/edit-violation-{id}" }, method = RequestMethod.POST)
    public String updateViolation(@Valid Violation violation, BindingResult result,
            ModelMap model, @PathVariable Integer id, Principal principal, HttpServletRequest request) {
		AbstractCompany comp=((User)request.getSession().getAttribute("currentuser")).getCompany();
		System.out.println("class:"+comp.getClass().getSimpleName());
		if(comp instanceof Rju || comp instanceof UnitDepart) {
			System.out.println("comp instanceof AbstractCompany || comp instanceof Rju || comp instanceof UnitDepart");
			model.addAttribute("hascompanies", true);
		} else if(comp instanceof Vstan || comp instanceof Underunitdep) {
			System.out.println("comp instanceof Vstan || comp instanceof Underunitdep");
			model.addAttribute("hascompanies", false);
		} else if(comp instanceof AbstractCompany){
			System.out.println("comp instanceof Vstan || comp instanceof UnderRju ");
			model.addAttribute("hascompanies", true);
		}
		Collection<AbstractCompany> listcompanies=initializeAbstractCompanies(principal.getName());
		model.addAttribute("title", "Талоны");
		model.addAttribute("jobs", initializeJobs());
		model.addAttribute("violguides", initializeViolguides());
		model.addAttribute("edit", true);
		model.addAttribute("amandatitle", "Талоны");
		model.addAttribute("bodytitle", "Создать запись");
		
        if (result.hasErrors()&&!result.getFieldError().getField().equals("violguide.conclviolguide")) {
        	System.out.println("FieldError: "+result.getFieldError());
			System.out.println("FieldErrorField: "+result.getFieldError().getField());
			System.out.println("FieldErrorCode: "+result.getFieldError().getCode());
			System.out.println("FieldErrorDeffaultMessage: "+result.getFieldError().getDefaultMessage());
            return "violations/createviolation";
        }
 
        AbstractCompany acompany=abstractCompanyService.findByCallName(violation.getCompany().getCallname());
        if(acompany==null || !listcompanies.contains(acompany)) {
			FieldError companyError = new FieldError("violation", "company.callname", messageSource.getMessage("not.exist.company.callname",
					new String[] { violation.getCompany().getCallname() }, Locale.getDefault()));
			result.addError(companyError);
			return "violations/createviolation";
		}
		String postname=violation.getPost().getPostname();
		PostJob job = postjobService.findByPostName(postname);
		if(!postname.isEmpty() && job==null) {
			FieldError postError = new FieldError("violation", "post.postname", messageSource.getMessage("not.exist.post.postname",
					new String[] { violation.getPost().getPostname() }, Locale.getDefault()));
			result.addError(postError);
			return "violations/createviolation";
		}
		Violguide guide = violguideService.findByContentViol(violation.getViolguide().getContentViol());
		if(guide==null) {
			FieldError guideError = new FieldError("violation", "violguide.contentViol", messageSource.getMessage("not.exist.violguide.contentViol",
					new String[] { violation.getViolguide().getContentViol() }, Locale.getDefault()));
			result.addError(guideError);
			return "violations/createviolation";
		}

		violation.setCompany(acompany);
		violation.setPost(job);
		violation.setViolguide(guide);

        violationService.updateViolation(violation);
        return "violations/violsuccess";
    }
	
	@RequestMapping(value = { "/violations/delete-violation-{id}" }, method = RequestMethod.GET)
    public String deleteViolation(@PathVariable Integer id) {
		violationService.deleteViolation(id);
        return "redirect:/violations/violationlist";
    }

	public List<PostJob> initializeJobs() {
		return postjobService.findAllPostJobs();

	}

	public List<Violguide> initializeViolguides() {
		return violguideService.findAllViolguides();
	}

	public Collection<AbstractCompany> initializeAbstractCompanies(String ssoId) {
		User user = userService.findBySSO(ssoId);
		AbstractCompany company = user.getCompany();
		Collection<AbstractCompany> companies = null;
		if (company instanceof Rju) {
			companies = new ArrayList<AbstractCompany>();
			companies.addAll(((Rju) company).getvStanCollection());
			companies.addAll(((Rju) company).getUnderRjuCollection());
		} else if (company instanceof UnitDepart) {
			companies = new ArrayList<AbstractCompany>();
			companies.addAll(((UnitDepart) company).getUnderunitdepCollection());
		} else if (company instanceof Underunitdep) {
			companies = new ArrayList<AbstractCompany>();
			companies.add((Underunitdep)company);
		} else if (company instanceof Underrju) {
			companies = new ArrayList<AbstractCompany>();
			companies.add((Underrju)company);
		} else if (company instanceof Vstan) {
			companies = new ArrayList<AbstractCompany>();
			companies.add((Vstan)company);
		} else if (company instanceof AbstractCompany){
			companies=abstractCompanyService.findAllAbstractCompanys();
		} 
		return companies;
	}
	@RequestMapping(value = { "/searchViolaion" }, method = RequestMethod.GET)
	public String searchViolations(@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "workat", required = false) String workat, 
			@RequestParam(value = "stubnum", required = false) Integer stubnum,
			@RequestParam(value = "dateseize", required = false) Date dateseize, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, ModelMap model, HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("currentuser");
		AbstractCompany company=null;
		if(!workat.isEmpty()&&workat!=null){
			company=abstractCompanyService.findByCallName(workat);
		}else {
			company=user.getCompany();
		}
		List<AbstractCompany> complist=abstractCompanyService.listChildCompanyOfParticularCompany(company);
		model.addAttribute("violationlist", 
				violationService.findAllViolations(complist, 
						surname,  stubnum, dateseize, dateseize2));
		model.addAttribute("title", "Талоны");
		model.addAttribute("amandatitle", "Талоны");
		return "violations/violationlist";
	}
	
	@RequestMapping(value = { "/usearchViolaion" }, method = RequestMethod.GET)
	public String usearchViolations(@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "stubnum", required = false) Integer stubnum,
			@RequestParam(value = "dateseize", required = false) Date dateseize, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, ModelMap model, HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("currentuser");
		AbstractCompany company=user.getCompany();
		List<AbstractCompany> complist=abstractCompanyService.listChildCompanyOfParticularCompany(company);
		model.addAttribute("violationlist", violationService.findAllViolations(complist, surname,  stubnum, dateseize, dateseize2));
		model.addAttribute("title", "Талоны");
		model.addAttribute("amandatitle", "Талоны");
		return "violations/violationlist";
	}
	
	@RequestMapping(value = "/violations/violationlistpdf", method = RequestMethod.GET)
    public ModelAndView downloadPdf(HttpServletRequest request) {
		AbstractCompany company=((User)request.getSession().getAttribute("currentuser")).getCompany();
		List<Violation> violationlist =violationService.findAllViolations(company);
        return new ModelAndView("pdfView", "listViolations", violationlist);
    }
	
	@RequestMapping(value = { "/reportViolaion" }, method = RequestMethod.GET)
	public String reportPDFViolations(@RequestParam(value = "workat", required = false) String workat, 
			@RequestParam(value = "stubnum", required = false) Integer stubnum,
			@RequestParam(value = "dateseize", required = false) Date dateseize, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, ModelMap model, HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("currentuser");
		AbstractCompany company=null;
		if(!workat.isEmpty()&&workat!=null){
			company=abstractCompanyService.findByCallName(workat);
		}else {
			company=user.getCompany();
		}
		model.addAttribute("company", company);
		List<AbstractCompany> complist=abstractCompanyService.listChildCompanyOfParticularCompany(company);
		model.addAttribute("listViolations", violationService.findAllViolations(complist,  stubnum, dateseize, dateseize2));
		model.addAttribute("date1", dateseize);
		model.addAttribute("date2", dateseize2);

		return "pdfView";
	}
	
	@RequestMapping(value = { "/ureportViolaion" }, method = RequestMethod.GET)
	public String ureportPDFViolations(@RequestParam(value = "stubnum", required = false) Integer stubnum,
			@RequestParam(value = "dateseize", required = false) Date dateseize, 
			@RequestParam(value = "dateseize2", required = false) Date dateseize2, ModelMap model, HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("currentuser");
		AbstractCompany company=user.getCompany();
		List<AbstractCompany> complist=abstractCompanyService.listChildCompanyOfParticularCompany(company);
		model.addAttribute("listViolations", violationService.findAllViolations(complist,  stubnum, dateseize, dateseize2));
		model.addAttribute("date1", dateseize);
		model.addAttribute("date2", dateseize2);

		return "pdfView";
	}
}
