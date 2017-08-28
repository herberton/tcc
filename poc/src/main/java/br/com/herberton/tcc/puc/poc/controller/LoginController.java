package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness.TICKET_COOKIE_NAME;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
	public String login(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response, UserDTO user) {
		
		String toIndex = "redirect:index";
		String toHome = "redirect:home";
		
		if(ticket != null && loginBusiness.getLoggedUser(ticket) != null) {
			return toHome;
		}
		
		ticket = loginBusiness.login(user);
		
		if(ticket == null) {
			return toIndex;
		}
		
		Cookie cookie = new Cookie(TICKET_COOKIE_NAME, ticket);
		response.addCookie(cookie);
		
		return toHome;
		
	}
	
}