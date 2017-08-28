package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness.TICKET_COOKIE_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
public class HomeController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private ILoginBusiness loginBusiness;

	
	@RequestMapping("/home")
	public String home(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, Model model) {

		UserDTO user = loginBusiness.getLoggedUser(ticket);
		
		if (user == null) {
			return "redirect:index";
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		
		model.addAttribute("user", user);
		model.addAttribute("networkAddress", networkAddress);
		
		return "home";

	}

}