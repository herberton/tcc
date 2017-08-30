package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness.TICKET_COOKIE_NAME;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IRegistrationBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.RegistrationUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private ILoginBusiness loginBusiness;
	
	@Autowired
	private IRegistrationBusiness registrationBusiness;

	
	@RequestMapping("/ecommerce-user")
	public String ecommerceUser(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, Model model) {
		
		LoggedUserDTO user = loginBusiness.getLoggedUser(ticket);;
		
		if (user == null) {
			return "redirect:index"; // toIndexController
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		
		model.addAttribute("user", user);
		model.addAttribute("networkAddress", networkAddress);
		
		return "registration/ecommerce-user"; // toEcommerceUserPage

	}
	
	
	@RequestMapping("/ecommerce-user/save")
	public String saveEcommerceUser(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, RegistrationUserDTO dto, Model model) {
		
		String toRegistrationEcommerceUserController = "redirect:registration/ecommerce-user";
		
		if(dto.isEmpty()) {
			return toRegistrationEcommerceUserController;
		}
		
		LoggedUserDTO loggedUser = defaultIfNull(loginBusiness.getLoggedUser(ticket), new LoggedUserDTO());
		
		if (!loggedUser.getIsAdmin() 
				&& !loggedUser.equals(new LoggedUserDTO(dto))) {
			return toRegistrationEcommerceUserController;
		}
		
		loggedUser = registrationBusiness.save(dto);
		
		if(loggedUser == null) {
			return toRegistrationEcommerceUserController;
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("networkAddress", networkAddress);
		
		return toRegistrationEcommerceUserController;

	}

}