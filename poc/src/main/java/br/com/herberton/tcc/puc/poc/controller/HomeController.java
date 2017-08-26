package br.com.herberton.tcc.puc.poc.controller;

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
	public String home(@CookieValue(name="JSESSIONID", required=false) String jSessionId, Model model) {

		UserDTO user = loginBusiness.getLoggedUser(jSessionId);
		
		if (user == null) {
			return "redirect:index";
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		
		model.addAttribute("user", user);
		model.addAttribute("networkAddress", networkAddress);
		
		return "home";

	}

}