package br.com.herberton.tcc.puc.poc.controller.registration;

import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofError;
import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofSuccess;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.converter.LoggedUserDTO2EcommerceUserDTOConverter;
import br.com.herberton.tcc.puc.poc.dto.MessageDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;
import br.com.herberton.tcc.puc.poc.service.contract.IEcommerceUserService;

@Controller
@RequestMapping("/registration/ecommerce-user")
public class EcommerceUserController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private IEcommerceUserService ecommerceUserService;

	@Autowired
	private LoggedUserDTO2EcommerceUserDTOConverter loggedUserDTO2EcommerceUserDTOConverter;
	
	@Autowired
	private ICookieHelper cookieHelper;
	
	
	@RequestMapping("/")
	public String ecommerceUser(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String networkAddress = networkHelper.getNetworkAddress();
		LoggedUserDTO loggedUser = this.authenticationService.getLoggedUser(ticket);
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		EcommerceUserDTO ecommerceUser = (EcommerceUserDTO)request.getAttribute("ecommerceUser");
		TicketDTO newTicket = (TicketDTO)request.getAttribute("newTicket");
		
		if((loggedUser == null || !loggedUser.getIsAdmin()) && newTicket != null) {
			
			loggedUser = this.authenticationService.getLoggedUser(newTicket.getValue());
			
			Cookie cookie = this.cookieHelper.newTicketCookie(newTicket);
			response.addCookie(cookie);
			
		}
		
		if(ecommerceUser == null && loggedUser != null && !loggedUser.getIsAdmin()) {
			ecommerceUser = loggedUserDTO2EcommerceUserDTOConverter.convert(loggedUser);
		}
		
		model.addAttribute("networkAddress", networkAddress);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("ecommerceUser", ecommerceUser);
		model.addAttribute("message", message);
		
		return "registration/ecommerce-user/form";
		
	}
	
	
	@RequestMapping("/form/save")
	public String saveEcommerceUser(HttpServletRequest request, EcommerceUserDTO ecommerceUser) {
		
		String toRegistrationEcommerceUserController = "forward:/registration/ecommerce-user/";
		
		if(ecommerceUser.isEmpty()) {
			return toRegistrationEcommerceUserController;
		}
		
		try {
			
			TicketDTO ticketDTO = ecommerceUserService.save(ecommerceUser);
			
			request.setAttribute("newTicket", ticketDTO);
			request.setAttribute("message", ofSuccess("Dados cadastrados com sucesso!"));
			
		} catch (Exception e) {

			request.setAttribute("ecommerceUser", ecommerceUser);
			request.setAttribute("message", ofError(e));
			
		}
		
		return toRegistrationEcommerceUserController;

	}

}