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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ivc.talonsystem.entity.PostJob;
import com.ivc.talonsystem.service.PostJobService;

@Controller
public class PostJobController {
	@Autowired
	MessageSource messageSource;

	@Autowired
	PostJobService service;

	@RequestMapping(method = RequestMethod.GET, value = "/jsonPosts", produces = "application/json")
	public @ResponseBody List<PostJob> generateJSONPosts(@RequestParam String postName){
		return service.findAllPostJobsWhereNameLike(postName);
	}
	@RequestMapping(value = { "/job/list" }, method = RequestMethod.GET)
	public ModelAndView listPostJobs(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("postjob/postjoblist");
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<PostJob> joblist = service.findAllPostJobs((page - 1) * recordsPerPage, recordsPerPage);
		model.addObject("jobs", joblist);
		int noOfRecords = service.findAllPostJobs().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("title", "Профессии");
		model.addObject("amandatitle", "Профессии");
		model.addObject("bodytitle", "Создать запись");
		return model;
	}

	@RequestMapping(value = { "/job/createjob" }, method = RequestMethod.GET)
	public String createPostJob(ModelMap model) {
		PostJob postjob = new PostJob();
		model.addAttribute("title", "Профессии");
		model.addAttribute("edit", false);
		model.addAttribute("amandatitle", "Профессии");
		model.addAttribute("bodytitle", "Создать запись");
		model.addAttribute("postJob", postjob);
		return "postjob/createpostjob";
	}

	/**
	 * @param postjob
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/job/createjob" }, method = RequestMethod.POST)
	public String savePostJob(@Valid PostJob postJob, BindingResult result, ModelMap model) {
		model.addAttribute("title", "Профессии");
		model.addAttribute("amandatitle", "Профессии");
		if (result.hasErrors()) {
			return "postjob/createpostjob";
		}
		if (!service.isPostJobNameUnique(postJob.getPostname().trim())) {
			FieldError postError = new FieldError("postJob", "postname", messageSource
					.getMessage("non.unique.postname", new String[] { postJob.getPostname() }, Locale.getDefault()));
			result.addError(postError);
			model.addAttribute("postJob", postJob);
			return "postjob/createpostjob";
		}
		service.savePostJob(postJob);
		model.addAttribute("success", "Профессия "+postJob.getPostname() +" успешно добавлена!");
		return "postjob/postjobsuccess";

	}

	@RequestMapping(value = { "/job/edit-job-{id}" }, method = RequestMethod.GET)
	public String editPostJob(@PathVariable Integer id, ModelMap model) {
		PostJob postJob = service.findById(id);
		model.addAttribute("postJob", postJob);
		model.addAttribute("edit", true);
		model.addAttribute("title", "Профессии");
		model.addAttribute("amandatitle", "Профессии");
		return "postjob/createpostjob";
	}

	@RequestMapping(value = { "/job/edit-job-{id}" }, method = RequestMethod.POST)
	public String updateCviol(@Valid PostJob postJob, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("title", "Профессии");
		model.addAttribute("amandatitle", "Профессии");
		if (result.hasErrors()) {
			return "postjob/createpostjob";
		}
		
		if ((!service.findById(id).getPostname().equals(postJob.getPostname().trim()))&&(!service.isPostJobNameUnique(postJob.getPostname().trim()))) {
			FieldError postError = new FieldError("postJob", "postname", messageSource
					.getMessage("non.unique.postname", new String[] { postJob.getPostname() }, Locale.getDefault()));
			result.addError(postError);
			model.addAttribute("postJob", postJob);
			return "postjob/createpostjob";
		}
		service.updatePostJob(postJob);
		model.addAttribute("success", "Профессия "+postJob.getPostname() +" успешно обновлена!");
		return "postjob/postjobsuccess";
	}

	@RequestMapping(value = { "/job/delete-job-{postname}" }, method = RequestMethod.GET)
	public String deletePost(@PathVariable String postname) {
		service.delete(postname);
		return "redirect:/job/list";
	}
	
	@RequestMapping(value="/searchPostJob", method = RequestMethod.GET)
	public String searchJobPostByName(@RequestParam(value = "postjobname", required = false) String postjobname, ModelMap model) {
		model.addAttribute("jobs", service.findAllPostJobsWhereNameLike(postjobname));
		model.addAttribute("title", "Профессии");
		model.addAttribute("amandatitle", "Профессии");
		model.addAttribute("bodytitle", "Создать запись");
		return "postjob/postjoblist";
	}
}
