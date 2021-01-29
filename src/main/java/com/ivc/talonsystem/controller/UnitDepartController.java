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

import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.service.UnitDepartService;

@Controller
public class UnitDepartController {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UnitDepartService service;
	
	
	@RequestMapping(value = { "/unitdepart/list" }, method = RequestMethod.GET)
	public ModelAndView listUnitdeparts(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("unitdepart/unitlist");
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<UnitDepart> unitlist=service.findAllUnits((page - 1) * recordsPerPage, recordsPerPage);
		model.addObject("unitdeparts", unitlist);
		int noOfRecords = service.findAllUnitDeparts().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("title", "Службы");
		model.addObject("amandatitle", "Службы");
		model.addObject("bodytitle", "Службы");
		return model;
	}

	@RequestMapping(value = { "/unitdepart/createunitdepart" }, method = RequestMethod.GET)
	public String createUnitdepart(ModelMap model) {
		UnitDepart unitDepart=new UnitDepart();
		model.addAttribute("title", "Службы");
		model.addAttribute("amandatitle", "Службы");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", false);
		model.addAttribute("unitDepart", unitDepart);
		return "unitdepart/createunit";
	}
	
	@RequestMapping(value = { "/unitdepart/createunitdepart" }, method = RequestMethod.POST)
	public String saveUnitdepart(@Valid UnitDepart unitDepart,  BindingResult result, ModelMap model) {
		model.addAttribute("amandatitle", "Службы");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("unitDepart", unitDepart);
		if (result.hasErrors()) {
			System.out.println("FieldError: "+result.getFieldError());
			System.out.println("FieldErrorField: "+result.getFieldError().getField());
			System.out.println("FieldErrorCode: "+result.getFieldError().getCode());
			System.out.println("FieldErrorDeffaultMessage: "+result.getFieldError().getDefaultMessage());
			return "unitdepart/createunit";
		}
		if (!service.isUnitDepartNameUnique(unitDepart.getCallname().trim())) {
			FieldError unitNameError = new FieldError("unitDepart", "callname", messageSource
					.getMessage("non.unique.unitdepart.callname", new Object[] { unitDepart.getCallname()}, Locale.getDefault()));
			result.addError(unitNameError);
			return "unitdepart/createunit";
		}
		unitDepart.setNameUnit(unitDepart.getCallname());
		service.saveUnitDepart(unitDepart);
		model.addAttribute("success",
				"Служба - "+unitDepart.getCallname()+" успешно добавлен!");
		return "unitdepart/unitsuccess";
		
	}
	
	@RequestMapping(value = { "/unitdepart/edit-unitdepart-{id}" }, method = RequestMethod.GET)
	public String editUnitdepart(@PathVariable Integer id, ModelMap model) {
		UnitDepart unitDepart=service.findById(id);
		model.addAttribute("unitDepart", unitDepart);
		model.addAttribute("title", "Службы");
		model.addAttribute("amandatitle", "Службы");
		model.addAttribute("edit", true);
		return "unitdepart/createunit";
	}
	
	@RequestMapping(value = { "/unitdepart/edit-unitdepart-{id}" }, method = RequestMethod.POST)
    public String updateUnitdepart(@Valid UnitDepart unitDepart, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("title", "Службы");
		model.addAttribute("amandatitle", "Службы");
		model.addAttribute("unitDepart", unitDepart);
		model.addAttribute("edit", true);
		if (result.hasErrors()) {
			return "unitdepart/createunit";
		}
		if ((!service.isUnitDepartNameUnique(unitDepart.getCallname().trim()))&&
				!service.findById(id).getCallname().equalsIgnoreCase(unitDepart.getCallname().trim())) {
			FieldError unitNameError = new FieldError("unitDepart", "callname", messageSource
					.getMessage("non.unique.unitdepart.callname", new Object[] { unitDepart.getCallname()}, Locale.getDefault()));
			result.addError(unitNameError);
			return "unitdepart/createunit";
		}
		unitDepart.setNameUnit(unitDepart.getCallname());
		service.updateUnitDepart(unitDepart);
		model.addAttribute("success",
				"Служба - "+unitDepart.getCallname()+" успешно обновлен!");
		return "unitdepart/unitsuccess";
    }
	
	@RequestMapping(value = { "/unitdepart/delete-unitdepart-{id}"}, method = RequestMethod.GET)
	public String deleteUnitDepart(@PathVariable Integer id) {
		service.deleteUnitDepartById(id);
		return "redirect:/unitdepart/list";
	}
	
	@RequestMapping(value="/searchUnit", method = RequestMethod.GET)
	public String searchUnitbyName(@RequestParam(value = "unitname", required = false) String unitname, ModelMap model) {
		model.addAttribute("unitdeparts", service.findAllUnitsWhereNameLike(unitname));
		model.addAttribute("title", "Службы");
		model.addAttribute("amandatitle", "Службы");
		model.addAttribute("bodytitle", "Службы");
		return "unitdepart/unitlist";
	}
}
