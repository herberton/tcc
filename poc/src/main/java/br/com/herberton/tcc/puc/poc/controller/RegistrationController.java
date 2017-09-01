package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofError;
import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofSuccess;
import static br.com.herberton.tcc.puc.poc.dto.TicketDTO.from;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IRegistrationBusiness;
import br.com.herberton.tcc.puc.poc.converter.LoggedUserDTO2EcommerceUserDTOConverter;
import br.com.herberton.tcc.puc.poc.dto.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.MessageDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private IAuthenticationBusiness loginBusiness;
	
	@Autowired
	private IRegistrationBusiness registrationBusiness;

	@Autowired
	private LoggedUserDTO2EcommerceUserDTOConverter loggedUserDTO2EcommerceUserDTOConverter;
	
	@Autowired
	private ICookieHelper cookieHelper;
	
	
	@RequestMapping("/ecommerce-user")
	public String ecommerceUser(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String networkAddress = networkHelper.getNetworkAddress();
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		
		EcommerceUserDTO ecommerceUser = (EcommerceUserDTO)request.getAttribute("ecommerceUser");
		
		TicketDTO newTicket = (TicketDTO)request.getAttribute("newTicket");
		
		LoggedUserDTO loggedUser = loginBusiness.getLoggedUser(newTicket == null ? from(ticket) : newTicket);
		
		if(ecommerceUser == null && loggedUser != null && !loggedUser.getIsAdmin()) {
			ecommerceUser = loggedUserDTO2EcommerceUserDTOConverter.convert(loggedUser);
		}
		
		if (newTicket != null) {
			Cookie cookie = this.cookieHelper.newTicketCookie(newTicket);
			response.addCookie(cookie);
		}
		
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("networkAddress", networkAddress);
		model.addAttribute("message", message);
		model.addAttribute("ecommerceUser", ecommerceUser);
		
		return "registration/ecommerce-user";
		
	}
	
	
	@RequestMapping("/ecommerce-user/save")
	public String saveEcommerceUser(HttpServletRequest request, EcommerceUserDTO ecommerceUser) {
		
		String toRegistrationEcommerceUserController = "forward:/registration/ecommerce-user";
		
		if(ecommerceUser.isEmpty()) {
			return toRegistrationEcommerceUserController;
		}
		
		try {
			
			TicketDTO ticketDTO = registrationBusiness.save(ecommerceUser);
			
			request.setAttribute("newTicket", ticketDTO);
			request.setAttribute("message", ofSuccess("Dados cadastrados com sucesso!"));
			
		} catch (Exception e) {

			request.setAttribute("ecommerceUser", ecommerceUser);
			request.setAttribute("message", ofError(e));
			
		}
		
		return toRegistrationEcommerceUserController;

	}

}