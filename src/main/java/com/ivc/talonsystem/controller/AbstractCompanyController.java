package com.ivc.talonsystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivc.talonsystem.DTO.AbstractCompanyDTO;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.service.AbstractCompanyService;

@Controller
public class AbstractCompanyController {
	
	@Autowired
	AbstractCompanyService abstractCompanyService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/jsonCompanies", produces = "application/json")
	public @ResponseBody List<AbstractCompanyDTO> generateJSONAbstractCompanies(@RequestParam String workat, HttpServletRequest request){
		AbstractCompany company=((User)request.getSession().getAttribute("currentuser")).getCompany();
		return abstractCompanyService.listChildCompanyOfParticularCompany(company, workat);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/jsonAllCompanies", produces = "application/json")
	public @ResponseBody List<AbstractCompanyDTO> generateJSONAbstractCompanies(@RequestParam String workat){
		return abstractCompanyService.findAllAbstractCompaniesWhereNameLike(workat);
	}

	
}
