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
import org.springframework.web.servlet.ModelAndView;

import com.ivc.talonsystem.entity.ConclusionViolGuide;
import com.ivc.talonsystem.service.ConclusionViolGuideService;

@Controller
public class ConclusionViolGuideController {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ConclusionViolGuideService service;
	
	
	@RequestMapping(value = { "/cviol/list" }, method = RequestMethod.GET)
	public ModelAndView listCviols(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("conclviol/cviollist");
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<ConclusionViolGuide> cviollist=service.findAllConclusionViolGuides((page - 1) * recordsPerPage, recordsPerPage);
		model.addObject("cviols", cviollist);
		int noOfRecords = service.findAllConclusionViolGuides().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("title", "Обобщенные нарушения");
		model.addObject("amandatitle", "Обобщенные нарушения");
		model.addObject("bodytitle", "Создать запись");
		return model;
	}

	@RequestMapping(value = { "/cviol/createcviol" }, method = RequestMethod.GET)
	public String createCviol(ModelMap model) {
		ConclusionViolGuide conclusionviolguide=new ConclusionViolGuide();
		model.addAttribute("title", "Обобщенные нарушения");
		model.addAttribute("amandatitle", "Обобщенные нарушения");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", false);
		model.addAttribute("conclusionViolGuide", conclusionviolguide);
		return "conclviol/createcviol";
	}
	
	@RequestMapping(value = { "/cviol/createcviol" }, method = RequestMethod.POST)
	public String saveCviol(@Valid ConclusionViolGuide conclusionViolGuide,  BindingResult result, ModelMap model) {
		model.addAttribute("title", "Обобщенные нарушения");
		model.addAttribute("amandatitle", "Обобщенные нарушения");
		model.addAttribute("conclusionViolGuide", conclusionViolGuide);
		if (result.hasErrors()) {
			return "conclviol/createcviol";
		}
		if (!service.isContentUnique(conclusionViolGuide.getContent().trim())) {
			FieldError contentError = new FieldError("conclusionViolGuide", "content", messageSource
					.getMessage("non.unique.conclusionviolguide.content", new Object[] { conclusionViolGuide.getContent() }, Locale.getDefault()));
			result.addError(contentError);
			return "conclviol/createcviol";
		}
		service.save(conclusionViolGuide);
		model.addAttribute("success",
				"Обобщенное нарушение успешно добавлен!");
		return "conclviol/cviolsuccess";
		
	}
	
	@RequestMapping(value = { "/cviol/edit-cviol-{id}" }, method = RequestMethod.GET)
	public String editCviol(@PathVariable Integer id, ModelMap model) {
		ConclusionViolGuide conclusionViolGuide=service.findById(id);
		System.out.println("CONCLUSIONVIOL"+conclusionViolGuide);
		model.addAttribute("conclusionViolGuide", conclusionViolGuide);
		model.addAttribute("title", "Обобщенные нарушения");
		model.addAttribute("amandatitle", "Обобщенные нарушения");
		model.addAttribute("edit", true);
		return "conclviol/createcviol";
	}
	
	@RequestMapping(value = { "/cviol/edit-cviol-{id}" }, method = RequestMethod.POST)
    public String updateCviol(@Valid ConclusionViolGuide conclusionViolGuide, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("title", "Обобщенные нарушения");
		model.addAttribute("amandatitle", "Обобщенные нарушения");
		model.addAttribute("conclusionViolGuide", conclusionViolGuide);
		model.addAttribute("edit", true);
		if (result.hasErrors()) {
			return "conclviol/createcviol";
		}
		if ((!service.isContentUnique(conclusionViolGuide.getContent().trim()))&&
				!service.findById(id).getContent().equalsIgnoreCase(conclusionViolGuide.getContent().trim())) {
			FieldError contentError = new FieldError("conclusionViolGuide", "content", messageSource
					.getMessage("non.unique.conclusionviolguide.content", new String[] { conclusionViolGuide.getContent() }, Locale.getDefault()));
			result.addError(contentError);
			return "conclviol/createcviol";
		}
		service.edit(conclusionViolGuide);
		model.addAttribute("success",
				"Обобщенное нарушение успешно обновлен!");
		return "conclviol/cviolsuccess";
    }
	
	@RequestMapping(value = { "/cviol/delete-cviol-{id}" }, method = RequestMethod.GET)
	public String deleteCviol(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/cviol/list";
	}
}
