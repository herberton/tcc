package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;

@Controller
public class HomeController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private IAuthenticationService authenticationService;

	
	@RequestMapping("/home")
	public String home(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, Model model) {
		
		LoggedUserDTO loggedUser = (LoggedUserDTO)request.getAttribute("loggedUser");
		
		if(loggedUser == null) {
			loggedUser = this.authenticationService.getLoggedUser(ticket);
		}
		
		if (loggedUser == null) {
			return "redirect:index"; // toIndexController
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("networkAddress", networkAddress);
		
		return "home"; // toHomePage

	}

}