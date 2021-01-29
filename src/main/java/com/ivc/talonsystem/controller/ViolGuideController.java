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
import com.ivc.talonsystem.entity.Violguide;
import com.ivc.talonsystem.service.ConclusionViolGuideService;
import com.ivc.talonsystem.service.ViolguideService;

@Controller
public class ViolGuideController {

	@Autowired
	MessageSource messageSource;
	
	
	@Autowired
	ViolguideService violguideService;
	
	@Autowired
	ConclusionViolGuideService service;

	@RequestMapping(value = "/violguide/list", method = RequestMethod.GET)
	public ModelAndView listViolGuidess(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("violguide/violguidelist");
		List<Violguide> violguides=violguideService.findAllViolguides();
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<Violguide> listviolguides=violguideService.findAllViolguides((page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords=violguides.size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("violguides", listviolguides);
		model.addObject("title", "Нарушении");
		model.addObject("amandatitle", "Нарушении");
		model.addObject("bodytitle", "Создать запись");
		return model;
	}

	@RequestMapping(value = { "/violguide/newviolguide" }, method = RequestMethod.GET)
	public String createViolGuide(ModelMap model) {
		Violguide violguide=new Violguide();
		List<ConclusionViolGuide> conclist=service.findAllConclusionViolGuides();
		model.addAttribute("title", "Нарушении");
		model.addAttribute("violguide", violguide);
		model.addAttribute("conclist", conclist);
		model.addAttribute("edit", false);
		model.addAttribute("amandatitle", "Нарушении");
		model.addAttribute("bodytitle", "Создать запись");
		return "violguide/createviolguide";
	}

	@RequestMapping(value = { "/violguide/newviolguide" }, method = RequestMethod.POST)
	public String saveViolGuide(@Valid Violguide violguide,  BindingResult result, ModelMap model) {
		model.addAttribute("title", "Нарушении");
		model.addAttribute("amandatitle", "Нарушении");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", false);
		List<ConclusionViolGuide> conclist=service.findAllConclusionViolGuides();
		model.addAttribute("conclist", conclist);
		if (result.hasErrors()) {
			return "violguide/createviolguide";
		}
		ConclusionViolGuide concviol=service.findByContent(violguide.getConclviolguide().getContent());
		if(concviol==null) {
			FieldError concviolError = new FieldError("violguide", "conclviolguide.content", messageSource.getMessage("not.exist.conclviolguide.content",
					new String[] { violguide.getConclviolguide().getContent() }, Locale.getDefault()));
			result.addError(concviolError);
			return "violguide/createviolguide";
		}
		if (!violguideService.isViolguideContentUnique(violguide.getContentViol().trim())) {
			FieldError contentError = new FieldError("violguide", "contentViol", messageSource
					.getMessage("non.unique.contentViol", new String[] { violguide.getContentViol() }, Locale.getDefault()));
			result.addError(contentError);
			return "violguide/createviolguide";
		}
		violguide.setConclviolguide(concviol);
		violguideService.saveViolguide(violguide);
		model.addAttribute("success", "Нарушения: "+violguide.getContentViol() +" успешно добавлена!");
		return "violguide/violguidesuccess";
	}

	@RequestMapping(value = { "/violguide/edit-violguide-{id}" }, method = RequestMethod.GET)
	public String editViolguide(@PathVariable Integer id, ModelMap model) {
		Violguide violguide=violguideService.findById(id);
		model.addAttribute("violguide", violguide);
		List<ConclusionViolGuide> conclist=service.findAllConclusionViolGuides();
		model.addAttribute("conclist", conclist);
		model.addAttribute("title", "Нарушении");
		model.addAttribute("amandatitle", "Нарушении");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", true);
		return "violguide/createviolguide";
	}
	
	@RequestMapping(value = { "/violguide/edit-violguide-{id}" }, method = RequestMethod.POST)
    public String updateViolguide(@Valid Violguide violguide, BindingResult result,
            ModelMap model, @PathVariable Integer id) {
		model.addAttribute("title", "Нарушении");
		model.addAttribute("amandatitle", "Нарушении");
		model.addAttribute("bodytitle", "Создать запись");
		List<ConclusionViolGuide> conclist=service.findAllConclusionViolGuides();
		model.addAttribute("conclist", conclist);
		if (result.hasErrors()) {
			return "violguide/createviolguide";
		}
		if ((!violguideService.isViolguideContentUnique(violguide.getContentViol().trim()))&&
				(!violguideService.findById(id).getContentViol().equalsIgnoreCase(violguide.getContentViol().trim()))) {
			FieldError contentError = new FieldError("violguide", "contentViol", messageSource
					.getMessage("non.unique.contentViol", new String[] { violguide.getContentViol() }, Locale.getDefault()));
			result.addError(contentError);
			return "violguide/createviolguide";
		}
		ConclusionViolGuide concviol=service.findByContent(violguide.getConclviolguide().getContent());
		if(concviol==null) {
			FieldError concviolError = new FieldError("violguide", "conclviolguide.content", messageSource.getMessage("not.exist.conclviolguide.content",
					new String[] { violguide.getConclviolguide().getContent() }, Locale.getDefault()));
			result.addError(concviolError);
			return "violguide/createviolguide";
		}
		violguide.setConclviolguide(concviol);
		violguideService.updateViolguide(violguide);
		model.addAttribute("success", "Нарушения: "+violguide.getContentViol() +" успешно обновлена!");
		return "violguide/violguidesuccess";
     }
	
	@RequestMapping(value = { "/violguide/delete-violguide-{id}" }, method = RequestMethod.GET)
    public String deleteViolGuide(@PathVariable Integer id) {
		violguideService.deleteViolguideById(id);
		return "redirect:/violguide/list";
    }
}
