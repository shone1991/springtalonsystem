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

import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Underrju;
import com.ivc.talonsystem.service.RjuService;
import com.ivc.talonsystem.service.UnderRjuService;

@Controller
public class UnderRjuController {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	RjuService rjuService;
	
	@Autowired
	UnderRjuService underrjuservice;

	@RequestMapping(value = { "/underrju/list" }, method = RequestMethod.GET)
	public ModelAndView listUnderRjus(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("underrju/underrjulist");
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<Underrju> underrjulist=underrjuservice.findAllUnderRjus((page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = underrjuservice.findAllUnderrjus().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addObject("underrjus", underrjulist);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("title", "Предприятии при РЖУ");
		model.addObject("amandatitle", "Предприятии при РЖУ");
		return model;
	}

	@RequestMapping(value = { "/underrju/newunderrju" }, method = RequestMethod.GET)
	public String createUnderRju(ModelMap model) {
		Underrju underrju=new Underrju();
		model.addAttribute("title", "Предприятии при РЖУ");
		model.addAttribute("amandatitle", "Предприятии при РЖУ");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("edit", false);
		model.addAttribute("underrju", underrju);
		model.addAttribute("rjulist", rjuService.findAllRjus());
		return "underrju/createunderrju";
	}

	@RequestMapping(value = { "/underrju/newunderrju" }, method = RequestMethod.POST)
	public String saveUnderRju(@Valid Underrju underrju,  BindingResult result, ModelMap model) {
		model.addAttribute("title", "Предприятии при РЖУ");
		model.addAttribute("amandatitle", "Предприятии при РЖУ");
		model.addAttribute("rjulist", rjuService.findAllRjus());
		if (result.hasErrors()&&!result.getFieldError().getField().equals("rju.briefname")) {
			System.out.println("FieldError: "+result.getFieldError());
			System.out.println("FieldErrorField: "+result.getFieldError().getField());
			System.out.println("FieldErrorCode: "+result.getFieldError().getCode());
			System.out.println("FieldErrorDeffaultMessage: "+result.getFieldError().getDefaultMessage());
			
			return "underrju/createunderrju";
			
		}
		
		if (!underrjuservice.isUnderRjuNameUnique(underrju.getId(),underrju.getCallname().trim())) {
			FieldError callNameError = new FieldError("underrju", "callname", messageSource
					.getMessage("non.unique.callname", new String[] { underrju.getCallname() }, Locale.getDefault()));
			result.addError(callNameError);
			System.out.println("FieldError: "+callNameError);
			System.out.println("FieldErrorField: "+callNameError.getField());
			System.out.println("FieldErrorCode: "+callNameError.getCode());
			System.out.println("FieldErrorDeffaultMessage: "+callNameError.getDefaultMessage());
			return "underrju/createunderrju";
		}
		
		Rju rju=rjuService.findByCallName(underrju.getRju().getCallname());
		System.out.println("rju: "+rju.getBriefname());
		underrju.setName(underrju.getCallname());
		underrju.setRju(rju);
		underrjuservice.saveUnderrju(underrju);
		model.addAttribute("success",
				"Предприятие при "+rju.getCallname()+" - " + underrju.getCallname() + " успешно добавлен!");
		return "underrju/underrjusuccess";
		
	}

	@RequestMapping(value = { "/underrju/edit-underrju-{id}" }, method = RequestMethod.GET)
	public String editUnderRju(@PathVariable Integer id, ModelMap model) {
		Underrju underrju=underrjuservice.findById(id);
		model.addAttribute("underrju", underrju);
		model.addAttribute("rjulist", rjuService.findAllRjus());
		model.addAttribute("title", "Предприятии при РЖУ");
		model.addAttribute("amandatitle", "Предприятии при РЖУ");
		model.addAttribute("edit", true);
		return "underrju/createunderrju";
	}
	
	@RequestMapping(value = { "/underrju/edit-underrju-{id}" }, method = RequestMethod.POST)
    public String updateUnderRju(@Valid Underrju underrju, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("underrju", underrju);
		model.addAttribute("title", "Предприятии при РЖУ");
		model.addAttribute("amandatitle", "Предприятии при РЖУ");
		if (result.hasErrors()&&!result.getFieldError().getField().equals("rju.briefname")) {
			model.addAttribute("edit", true);
			return "underrju/createunderrju";
		}
		/*getunderrjubyid
		 * compare it with formunderrju by callname
		 * if callname is same then
		 * skip validation (in order to get callname field updatable)
		 * otherwise
		 * validate*/
		if((!underrjuservice.findById(id).getCallname().equalsIgnoreCase(underrju.getCallname().trim()))
				&&
				(!underrjuservice.isUnderRjuNameUnique(underrju.getId(),underrju.getCallname().trim()))) {
			FieldError callNameError = new FieldError("underrju", "callname", messageSource
					.getMessage("non.unique.callname", new String[] { underrju.getCallname() }, Locale.getDefault()));
			result.addError(callNameError);
			model.addAttribute("edit", true);
			return "underrju/createunderrju";
			
		}
		Rju rju=rjuService.findByCallName(underrju.getRju().getCallname());
		underrju.setName(underrju.getCallname());
		underrju.setRju(rju);
		underrjuservice.updateUnderrju(underrju);
		model.addAttribute("success",
				"Предприятие при "+underrju.getRju().getCallname()+" - " + underrju.getCallname() + " успешно обновлен!");
		return "underrju/underrjusuccess";
    }
	
	@RequestMapping(value = { "/underrju/delete-underrju-{id}" }, method = RequestMethod.GET)
    public String deleteUnderrju(@PathVariable Integer id) {
		underrjuservice.deleteUnderrjuById(id);
        return "redirect:/underrju/list";
    }
	
	@RequestMapping(value="/searchUnderRju", method = RequestMethod.GET)
	public String searchUnderUnitbyName(@RequestParam(value = "underrjuname", required = false) String underrjuname, ModelMap model) {
		model.addAttribute("underrjus", underrjuservice.findAllUnderRjusWhereNameLike(underrjuname));
		model.addAttribute("title", "Предприятии при РЖУ");
		model.addAttribute("amandatitle", "Предприятии при РЖУ");
		model.addAttribute("bodytitle", "Предприятии при РЖУ");
		return "underrju/underrjulist";
	}
}
