package com.ivc.talonsystem.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ivc.talonsystem.entity.Underunitdep;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.service.UnderUnitDepService;
import com.ivc.talonsystem.service.UnitDepartService;

@Controller
public class UnderUnitDepartController {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UnitDepartService unitDepartService;
	
	@Autowired
	UnderUnitDepService underUnitDepService;

	@RequestMapping(value = { "/underunitdep/list" }, method = RequestMethod.GET)
	public ModelAndView listUnderUnitdeps(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("underunitdep/underunitdeplist");
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<Underunitdep> underunitdeplist=underUnitDepService.findAllUnderUnits((page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = underUnitDepService.findAllUnderunitdeps().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addObject("underunitdeps", underunitdeplist);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("title", "Предприятии при Служб");
		model.addObject("amandatitle", "Предприятии при Служб");
		return model;
	}

	@RequestMapping(value = { "/underunitdep/newunderunitdep" }, method = RequestMethod.GET)
	public String createUnderUnitdep(ModelMap model) {
		Underunitdep underunitdep=new Underunitdep();
		model.addAttribute("title", "Предприятии при Служб");
		model.addAttribute("amandatitle", "Предприятии при Служб");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", false);
		model.addAttribute("underunitdep", underunitdep);
		model.addAttribute("underunitlist", unitDepartService.findAllUnitDeparts());
		return "underunitdep/createunderunitdep";
	}

	@RequestMapping(value = { "/underunitdep/newunderunitdep" }, method = RequestMethod.POST)
	public String saveUnderUnitdep(@Valid Underunitdep underunitdep,  BindingResult result, ModelMap model) {
		model.addAttribute("title", "Предприятии при РЖУ");
		model.addAttribute("amandatitle", "Предприятии при РЖУ");
		model.addAttribute("underunitdep", underunitdep);
		model.addAttribute("underunitlist", unitDepartService.findAllUnitDeparts());
		if (result.hasErrors()) {
			System.out.println("FieldError: "+result.getFieldError());
			System.out.println("FieldErrorField: "+result.getFieldError().getField());
			System.out.println("FieldErrorCode: "+result.getFieldError().getCode());
			System.out.println("FieldErrorDeffaultMessage: "+result.getFieldError().getDefaultMessage());
			
			return "underunitdep/createunderunitdep";
			
		}
		
		if (!underUnitDepService.isUnderunitdepNameUnique(underunitdep.getCallname().trim())) {
			FieldError callNameError = new FieldError("underUnitDepart", "callname", messageSource
					.getMessage("non.unique.underunitdep.callname", new String[] { underunitdep.getCallname() }, Locale.getDefault()));
			result.addError(callNameError);
			System.out.println("FieldError: "+callNameError);
			System.out.println("FieldErrorField: "+callNameError.getField());
			System.out.println("FieldErrorCode: "+callNameError.getCode());
			System.out.println("FieldErrorDeffaultMessage: "+callNameError.getDefaultMessage());
			return "underunitdep/createunderunitdep";
		}
		UnitDepart unitdepart=unitDepartService.findByCallName(underunitdep.getUnitdep().getCallname().trim());
		System.out.println("UNIT:_________"+unitdepart.getCallname());
		underunitdep.setName(underunitdep.getCallname());
		underunitdep.setUnitdep(unitdepart);
		underUnitDepService.saveUnderunitdep(underunitdep);
		model.addAttribute("success",
				"Предприятие при службы "+unitdepart.getCallname()+" - " + underunitdep.getCallname() + " успешно добавлен!");
		return "underunitdep/underunitdepsuccess";
		
	}

	@RequestMapping(value = { "/underunitdep/edit-underunitdep-{id}" }, method = RequestMethod.GET)
	public String editUnderUnitdep(@PathVariable Integer id, ModelMap model) {
		Underunitdep underunitdep=underUnitDepService.findById(id);
		model.addAttribute("underunitdep", underunitdep);
		model.addAttribute("underunitlist", unitDepartService.findAllUnitDeparts());
		model.addAttribute("title", "Предприятии при Служб");
		model.addAttribute("amandatitle", "Предприятии при Служб");
		model.addAttribute("edit", true);
		return "underunitdep/createunderunitdep";
	}
	
	@RequestMapping(value = { "/underunitdep/edit-underunitdep-{id}" }, method = RequestMethod.POST)
    public String updateUnderUnitdep(@Valid Underunitdep underunitdep, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("underunitdep", underunitdep);
		model.addAttribute("title", "Предприятии при Служб");
		model.addAttribute("amandatitle", "Предприятии при Служб");
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			return "underunitdep/createunderunitdep";
		}
		if((!underUnitDepService.findById(id).getCallname().equalsIgnoreCase(underunitdep.getCallname().trim()))
				&&
				(!underUnitDepService.isUnderunitdepNameUnique(underunitdep.getCallname().trim()))) {
			FieldError callNameError = new FieldError("underUnitDepart", "callname", messageSource
					.getMessage("non.unique.underunitdep.callname", new String[] { underunitdep.getCallname() }, Locale.getDefault()));
			result.addError(callNameError);
			System.out.println("FieldError: "+callNameError);
			System.out.println("FieldErrorField: "+callNameError.getField());
			System.out.println("FieldErrorCode: "+callNameError.getCode());
			System.out.println("FieldErrorDeffaultMessage: "+callNameError.getDefaultMessage());
			return "underunitdep/createunderunitdep";
			
		}
		UnitDepart unitdepart=unitDepartService.findByCallName(underunitdep.getUnitdep().getCallname().trim());
		underunitdep.setName(underunitdep.getCallname());
		underunitdep.setUnitdep(unitdepart);
		underUnitDepService.updateUnderunitdep(underunitdep);
		model.addAttribute("success",
				"Предприятие при службы "+unitdepart.getCallname()+" - " + underunitdep.getCallname() + " успешно обновлен!");
		return "underunitdep/underunitdepsuccess";
    }
	
	@RequestMapping(value = { "/underunitdep/delete-underunitdep-{id}" }, method = RequestMethod.GET)
    public String deleteUnderUnitdep(@PathVariable Integer id) {
		underUnitDepService.deleteUnderunitdepById(id);
        return "redirect:/underunitdep/list";
    }
	
	@RequestMapping(value="/searchUnderUnit", method = RequestMethod.GET)
	public String searchUnderUnitbyName(@RequestParam(value = "underunitname", required = false) String underunitname, ModelMap model) {
		model.addAttribute("underunitdeps", underUnitDepService.findAllUnderUnitsWhereNameLike(underunitname));
		model.addAttribute("title", "Предприятии при Служб");
		model.addAttribute("amandatitle", "Предприятии при Служб");
		model.addAttribute("bodytitle", "Предприятии при Служб");
		return "underunitdep/underunitdeplist";
	}
}
