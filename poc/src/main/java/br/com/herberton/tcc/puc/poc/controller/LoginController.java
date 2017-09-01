package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofError;
import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofSuccess;
import static br.com.herberton.tcc.puc.poc.dto.TicketDTO.from;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper;

@Controller
public class LoginController {

	@Autowired
	private IAuthenticationBusiness authenticationBusiness;
	
	@Autowired
	private ICookieHelper cookieHelper;

	
	@RequestMapping("/login")
	public String login(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response, RedirectAttributes redirectAttributes, LoginDTO login, Model model) {
		
		login = defaultIfNull(login, new LoginDTO());
		
		redirectAttributes.addAttribute("index", true);
		
		String toIndexController = "redirect:index";
		
		LoggedUserDTO loggedUser = authenticationBusiness.getLoggedUser(from(ticket));
		
		if(loggedUser != null) {
			if(login.isEmpty() || new LoginDTO(loggedUser).equals(login)) {
				return toIndexController;
			}
		} else if(login.isEmpty()) {
			return toIndexController;
		}
		
		try {
			
			TicketDTO ticketDTO = authenticationBusiness.login(login);
			
			Cookie cookie = this.cookieHelper.newTicketCookie(ticketDTO);
			
			response.addCookie(cookie);
			
			model.addAttribute("message", ofSuccess("Autenticação efetuada com sucesso!"));
			
		} catch (Exception e) {

			model.addAttribute("message", ofError(e));
			
		}
		
		return toIndexController;
		
	}
	
	
	@RequestMapping("/logout")
	public String logout(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletResponse response) {
		
		authenticationBusiness.logout(from(ticket));
		
		Cookie cookie = new Cookie(TICKET_COOKIE_NAME, null);
		cookie.setPath("/poc");
		response.addCookie(cookie);
		
		return "redirect:index"; // toIndexController
		
	}
	
}