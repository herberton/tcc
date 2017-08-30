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
import br.com.herberton.tcc.puc.poc.dto.UserDTO;

@Controller
public class LoginController {

	@Autowired
	private ILoginBusiness loginBusiness;

	
	@RequestMapping("/login")
	public String login(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response, RedirectAttributes redirectAttributes, UserDTO user, Model model) {
		
		user = defaultIfNull(user, new UserDTO());
		
		redirectAttributes.addAttribute("index", true);
		
		String toIndexController = "redirect:index";
		
		UserDTO loggedUser = loginBusiness.getLoggedUser(ticket);
		
		if(loggedUser != null) {
			if(user.isEmpty() || loggedUser.equals(user)) {
				return toIndexController;
			}
		} else if(user.isEmpty()) {
			return toIndexController;
		}
		
		ticket = loginBusiness.login(user);
		
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