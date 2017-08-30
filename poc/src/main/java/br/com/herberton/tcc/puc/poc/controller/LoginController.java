package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness.TICKET_COOKIE_NAME;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;

@Controller
public class LoginController {

	@Autowired
	private ILoginBusiness loginBusiness;

	
	@RequestMapping("/login")
	public String login(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response, RedirectAttributes redirectAttributes, LoginDTO login, Model model) {
		
		login = defaultIfNull(login, new LoginDTO());
		
		redirectAttributes.addAttribute("index", true);
		
		String toIndexController = "redirect:index";
		
		LoggedUserDTO loggedUser = loginBusiness.getLoggedUser(ticket);
		
		if(loggedUser != null) {
			if(login.isEmpty() || new LoginDTO(loggedUser).equals(login)) {
				return toIndexController;
			}
		} else if(login.isEmpty()) {
			return toIndexController;
		}
		
		ticket = loginBusiness.login(login);
		
		if(ticket != null) {
			Cookie cookie = new Cookie(TICKET_COOKIE_NAME, ticket);
			response.addCookie(cookie);
		}
		
		return toIndexController;
		
	}
	
	
	@RequestMapping("/logout")
	public String logout(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response) {
		
		loginBusiness.logout(ticket);
		
		Cookie cookie = new Cookie(TICKET_COOKIE_NAME, null);
		response.addCookie(cookie);
		
		return "redirect:index"; // toIndexController
		
	}
	
}