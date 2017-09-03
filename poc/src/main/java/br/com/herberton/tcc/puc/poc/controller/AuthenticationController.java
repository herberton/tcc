package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofError;
import static br.com.herberton.tcc.puc.poc.dto.TicketDTO.withTicket;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper;

@Controller
public class AuthenticationController {

	@Autowired
	private IAuthenticationBusiness authenticationBusiness;
	
	@Autowired
	private ICookieHelper cookieHelper;

	
	@RequestMapping("/login")
	public String login(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, LoginDTO login) {
		
		login = defaultIfNull(login, new LoginDTO());
		
		LoggedUserDTO loggedUser = authenticationBusiness.getLoggedUser(withTicket(ticket));
		
		if(loggedUser != null) {
			if(login.isEmpty() || new LoginDTO(loggedUser).equals(login)) {
				return this.redirectToIndexController(redirectAttributes);
			}
		}
			
		if(login.isEmpty()) {
			return this.redirectToIndexController(redirectAttributes);
		}
		
		try {
			
			TicketDTO ticketDTO = authenticationBusiness.login(login);
			
			Cookie cookie = this.cookieHelper.newTicketCookie(ticketDTO);
			
			response.addCookie(cookie);
			
			return this.redirectToIndexController(redirectAttributes);
			
		} catch (Exception e) {

			request.setAttribute("message", ofError(e));
			
			return "forward:/index"; // toIndexController;
			
		}
		
	}
	
	private String redirectToIndexController(RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("index", true);
		
		return "redirect:index";
	
	}
	
	
	@RequestMapping("/logout")
	public String logout(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response) {
		
		authenticationBusiness.logout(withTicket(ticket));
		
		Cookie cookie = new Cookie(TICKET_COOKIE_NAME, null);
		cookie.setPath("/poc");
		response.addCookie(cookie);
		
		return "redirect:index"; // toIndexController
		
	}
	
}