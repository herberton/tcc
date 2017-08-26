package br.com.herberton.tcc.puc.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;

@Controller
public class LoginController {
	 
	@Autowired
	private ILoginBusiness loginBusiness;
	
	
	@RequestMapping("/login")
	public String login(@CookieValue(name="JSESSIONID", required=false) String jSessionId, UserDTO user) {
		
		boolean isLogged = loginBusiness.login(jSessionId, user);
		
		if(!isLogged) {
			return "redirect:index";
		}
		
		return "redirect:home";
		
	}
	
}