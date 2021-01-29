package com.ivc.talonsystem.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.service.UserService;

@Controller
public class TestController {
	
	@Autowired
    UserService userService;

	@RequestMapping(value = { "/mypage" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
		User user = userService.findBySSO(getPrincipal());
		model.addAttribute("user", user);
		AbstractCompany company=user.getCompany();
		Collection<AbstractCompany> companies=null;
		if(company instanceof Rju) {
			companies=new ArrayList<AbstractCompany>();
			companies.addAll(((Rju)company).getvStanCollection());
			companies.addAll(((Rju)company).getUnderRjuCollection());
		}else if(company instanceof UnitDepart) {
			companies=new ArrayList<AbstractCompany>();
			companies.addAll(((UnitDepart)company).getUnderunitdepCollection());
		}
		model.addAttribute("listcompanies", companies);
        return "mypage";
    }
	
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
