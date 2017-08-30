package br.com.herberton.tcc.puc.poc.controller;

import static br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness.TICKET_COOKIE_NAME;
import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.ADMINISTRATOR;
import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.EMPLOYEE;
import static java.lang.Boolean.valueOf;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultString;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
public class IndexController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private ILoginBusiness loginBusiness;

	
	@RequestMapping({ "/", "/index" })
	public String index(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, Model model) {
		
		UserDTO user = loginBusiness.getLoggedUser(ticket);

		if (user == null) {
			return this.toIndexPage(user, model);
		}
		
		if(user.getRoles().contains(ADMINISTRATOR)) {
			
			Boolean index = valueOf(defaultString(request.getParameter("index"), "false").trim());
			
			if(index) {
				return this.toIndexPage(user, model);
			}
			
			return this.toHomeController(request, user);
			
		}
		
		if(user.getRoles().contains(EMPLOYEE)) {
			return this.toHomeController(request, user);
		}
		
		return this.toIndexPage(user, model);

	}

	private String toHomeController(HttpServletRequest request, UserDTO user) {
		
		request.setAttribute("user", user);
		
		return "forward:home";
		
	}
	
	private String toIndexPage(UserDTO user, Model model) {
		
		user = defaultIfNull(user, new UserDTO());
		
		if(!user.isEmpty()) {
			model.addAttribute("user", user);
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		model.addAttribute("networkAddress", networkAddress);
		
		return "index";
	
	}

}