package com.ivc.talonsystem.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.entity.UserProfile;
import com.ivc.talonsystem.service.AbstractCompanyService;
import com.ivc.talonsystem.service.UserProfileService;
import com.ivc.talonsystem.service.UserService;

@Controller
@SessionAttributes("roles")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	AbstractCompanyService abstractCompanyService;

	/**
	 * This method will list all existing users.
	 * 
	 * @param <T>
	 */
	@RequestMapping(value = { "/users/list" }, method = RequestMethod.GET)
	public <T> String listUsers(ModelMap model, HttpServletRequest request) {
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<User> users = userService.findAllUsers((page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = userService.findAllUsers().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("users", users);
		model.addAttribute("title", "Пользователи");
		model.addAttribute("amandatitle", "Пользователи");
		return "user/userlist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/users/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("companies", initializeCompanies());
		model.addAttribute("edit", false);
		model.addAttribute("title", "Пользователи");
		model.addAttribute("amandatitle", "Пользователи");
		return "user/createuser";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/users/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		model.addAttribute("title", "Пользователи");
		model.addAttribute("amandatitle", "Пользователи");
		if (result.hasErrors()) {
			model.addAttribute("companies", initializeCompanies());
			model.addAttribute("edit", true);
			return "user/createuser";
		}

		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			model.addAttribute("companies", initializeCompanies());
			model.addAttribute("edit", true);
			return "user/createuser";
		}
		AbstractCompany acompany = abstractCompanyService.findByCallName(user.getCompany().getCallname());
		if(acompany==null) {
			FieldError companyError = new FieldError("user", "company.callname", messageSource.getMessage("not.exist.company.callname",
					new String[] { user.getCompany().getCallname() }, Locale.getDefault()));
			result.addError(companyError);
			model.addAttribute("companies", initializeCompanies());
			model.addAttribute("edit", true);
			return "user/createuser";
		}
		user.setCompany(acompany);
		userService.saveUser(user);

		model.addAttribute("success",
				"Пользователь " + user.getFirstName() + " " + user.getLastName() + " успешно добавлен!");
		return "user/usersuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/users/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("companies", initializeCompanies());
		model.addAttribute("title", "Пользователи");
		model.addAttribute("amandatitle", "Пользователи");
		return "user/createuser";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/users/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId,
			HttpServletRequest request) {
		User currentuser=(User) request.getSession().getAttribute("currentuser");
		model.addAttribute("title", "Пользователи");
		model.addAttribute("amandatitle", "Пользователи");
		model.addAttribute("companies", initializeCompanies());
		if (result.hasErrors()) {
			
			if (user.getId().equals(currentuser.getId())) {
				user.setCompany(currentuser.getCompany());
				user.setFirstName(currentuser.getFirstName());
				user.setLastName(currentuser.getLastName());
				user.setUserProfiles(currentuser.getUserProfiles());
				userService.updateUser(user);
				return "redirect:/logout";
			}
		
			return "user/createuser";
		}


		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "user/createuser";
		}

		AbstractCompany acompany = abstractCompanyService.findByCallName(user.getCompany().getCallname());
		if(acompany==null) {
			FieldError companyError = new FieldError("user", "company.callname", messageSource.getMessage("not.exist.company.callname",
					new String[] { user.getCompany().getCallname() }, Locale.getDefault()));
			result.addError(companyError);
			return "user/createuser";
		}
		user.setCompany(acompany);
		userService.updateUser(user);
		model.addAttribute("success",
				"Пользователь " + user.getFirstName() + " " + user.getLastName() + " успешно обновлен!");
		
		return "user/usersuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/users/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/users/list";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	public List<AbstractCompany> initializeCompanies() {
		return abstractCompanyService.findAllAbstractCompanys();

	}
	
	@RequestMapping(value="/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "workat", required = false) String workat, ModelMap model) {
//		System.out.println("PARAMS--------------------: "+name.isEmpty()+surname+nickname+workat);
		AbstractCompany company=null;
		if(!workat.isEmpty()){
			company=abstractCompanyService.findByCallName(workat);
		}
		model.addAttribute("users", userService.findAllUsers(name, surname, nickname, company));
		model.addAttribute("title", "Пользователи");
		model.addAttribute("amandatitle", "Пользователи");
		model.addAttribute("bodytitle", "Пользователи");
		return "user/userlist";
	}
}
