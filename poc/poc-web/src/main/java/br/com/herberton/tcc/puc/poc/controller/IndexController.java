package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.ADMINISTRATOR;
import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.EMPLOYEE;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;
import static java.lang.Boolean.valueOf;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultString;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.dto.MessageDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;

@Controller
public class IndexController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private IAuthenticationService authenticationService;

	
	@RequestMapping({ "/", "/index" })
	public String index(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, Model model) {
		
		LoggedUserDTO loggedUser = this.authenticationService.getLoggedUser(ticket);

		if (loggedUser == null) {
			return this.toIndexPage(loggedUser, request, model);
		}
		
		if(loggedUser.getRoles().contains(ADMINISTRATOR)) {
			
			Boolean index = valueOf(defaultString(request.getParameter("index"), "false").trim());
			
			if(index) {
				return this.toIndexPage(loggedUser, request, model);
			}
			
			return this.toHomeController(request, loggedUser);
			
		}
		
		if(loggedUser.getRoles().contains(EMPLOYEE)) {
			return this.toHomeController(request, loggedUser);
		}
		
		return this.toIndexPage(loggedUser, request, model);

	}

	private String toHomeController(HttpServletRequest request, LoggedUserDTO loggedUser) {
		
		request.setAttribute("loggedUser", loggedUser);
		
		return "forward:home";
		
	}
	
	private String toIndexPage(LoggedUserDTO loggedUser, HttpServletRequest request, Model model) {
		
		loggedUser = defaultIfNull(loggedUser, new LoggedUserDTO());
		
		if(!loggedUser.isEmpty()) {
			model.addAttribute("loggedUser", loggedUser);
		}
		
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		model.addAttribute("message", message);
		
		String networkAddress = networkHelper.getNetworkAddress();
		model.addAttribute("networkAddress", networkAddress);
		
		return "index";
	
	}

}