package com.ivc.talonsystem.controller;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.service.AbstractCompanyService;
import com.ivc.talonsystem.service.RjuService;

@Controller
public class RjuController {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	AbstractCompanyService abstractCompanyService;
	
	@Autowired
	RjuService rjuService;

	@RequestMapping(value = { "/rju/list" }, method = RequestMethod.GET)
	public ModelAndView listRjus() {
		ModelAndView model = new ModelAndView("rju/rjulist");
		List<Rju> rjulist=rjuService.findAllRjus();
		model.addObject("rjus", rjulist);
		model.addObject("title", "РЖУ");
		model.addObject("amandatitle", "РЖУ");
		model.addObject("bodytitle", "Создать запись");
		return model;
	}

	@RequestMapping(value = { "/rju/createrju" }, method = RequestMethod.GET)
	public String createRju(ModelMap model) {
		Rju rju=new Rju();
		model.addAttribute("title", "РЖУ");
		model.addAttribute("amandatitle", "РЖУ");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", false);
		model.addAttribute("rju", rju);
		return "rju/createrju";
	}

	@RequestMapping(value = { "/rju/createrju" }, method = RequestMethod.POST)
	public String saveRju(@Valid Rju rju,  BindingResult result, ModelMap model) {
		model.addAttribute("title", "РЖУ");
		model.addAttribute("amandatitle", "РЖУ");
		model.addAttribute("rju", rju);
		if (result.hasErrors()) {
			return "rju/createrju";
		}
		if(!rjuService.isRjuNameUnique(rju.getCallname().trim())) {
			FieldError rjuNameError = new FieldError("rju", "callname", messageSource
					.getMessage("non.unique.rju.callname", new Object[] { rju.getCallname() }, Locale.getDefault()));
			result.addError(rjuNameError);
			return "rju/createrju";
		}
		rju.setNamerju(rju.getCallname());
		rjuService.saveRju(rju);
		model.addAttribute("success",
				"РЖУ " + rju.getCallname() + " успешно добавлен!");
		return "rju/rjusuccess";
		
	}

	@RequestMapping(value = { "/rju/edit-rju-{id}" }, method = RequestMethod.GET)
	public String editRju(@PathVariable Integer id, ModelMap model) {
		Rju rju=rjuService.findById(id);
		model.addAttribute("rju", rju);
		model.addAttribute("title", "РЖУ");
		model.addAttribute("amandatitle", "РЖУ");
		model.addAttribute("edit", true);
		return "rju/createrju";
	}
	
	@RequestMapping(value = { "/rju/edit-rju-{id}" }, method = RequestMethod.POST)
    public String updateRju(@Valid Rju rju, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("rju", rju);
		model.addAttribute("title", "РЖУ");
		model.addAttribute("amandatitle", "РЖУ");
		if (result.hasErrors()) {
			return "rju/createrju";
		}
		if((!rjuService.isRjuNameUnique(rju.getCallname().trim()))&&
				(!rjuService.findById(id).getCallname().equalsIgnoreCase(rju.getCallname().trim()))) {
			FieldError rjuNameError = new FieldError("rju", "callname", messageSource
					.getMessage("non.unique.rju.callname", new Object[] { rju.getCallname() }, Locale.getDefault()));
			result.addError(rjuNameError);
			return "rju/createrju";
		}
		rju.setNamerju(rju.getCallname());
		rjuService.updateRju(rju);
		model.addAttribute("success",
				rju.getCallname() + " успешно обновлен!");
		return "rju/rjusuccess";
    }
}
