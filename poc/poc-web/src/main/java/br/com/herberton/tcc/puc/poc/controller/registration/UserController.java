package br.com.herberton.tcc.puc.poc.controller.registration;

import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofError;
import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofSuccess;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.dto.MessageDTO;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;
import br.com.herberton.tcc.puc.poc.service.contract.IUserService;

@Controller
@RequestMapping("/registration/user")
public class UserController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping("/")
	public String user(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String toFormController = "forward:/registration/user/form";
		
		LoggedUserDTO loggedUser = this.authenticationService.getLoggedUser(ticket);
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../index"; // toIndexController
		}
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		UserDTO user = (UserDTO)request.getAttribute("user");
		
		if(loggedUser.getIsAdmin()) {
			
			if(message!= null && message.getIsError() && user != null) {
				return toFormController;
			}
			
			String networkAddress = networkHelper.getNetworkAddress();
			List<UserDTO> list = this.userService.listUsers().getList(); 
			
			model.addAttribute("message", message);
			model.addAttribute("loggedUser", loggedUser);
			model.addAttribute("networkAddress", networkAddress);
			model.addAttribute("list", list);
			
			return "registration/user/list"; // toListPage
		
		}
		
		return toFormController;
		
	}
	
	@RequestMapping("/delete")
	public String delete(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request) {
		
		LoggedUserDTO loggedUser = this.authenticationService.getLoggedUser(ticket);
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../index"; // toIndexController
		}
		
		int id = parseInt(defaultString(request.getParameter("id"), "0"));
		
		if(loggedUser.getIsAdmin() && id != 0) {
			
			try {
				
				this.userService.delete(id);
				
				request.setAttribute("message", ofSuccess("Registro excluido com sucesso!"));
					
			} catch (Exception e) {
				
				request.setAttribute("message", ofError(e));
			
			}
			
		}
		
		return "forward:/registration/user/"; // toListController
		
	}
	
	@RequestMapping("/form")
	public String form(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request,  Model model) {
		
		LoggedUserDTO loggedUser = this.authenticationService.getLoggedUser(ticket);
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../index"; // toIndexController
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		List<RoleDTO> roles = this.userService.listRoles().getList();
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		UserDTO user = (UserDTO)request.getAttribute("user");
		
		if(user == null) {
			
			int id = 
				loggedUser.getIsAdmin() ? 
					parseInt(defaultString(request.getParameter("id"), "0")) : 
					loggedUser.getId();
			
			user = id == 0 ? null : this.userService.find(id);
			
		}
		
		model.addAttribute("message", message);
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("networkAddress", networkAddress);
		
		return "registration/user/form"; // toFormPage
		
	}
	
	@RequestMapping("/form/save")
	public String save(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, UserDTO user) {
		
		LoggedUserDTO loggedUser = this.authenticationService.getLoggedUser(ticket);
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../../index"; // toIndexController
		}
		
		String toRegistrationEcommerceUserController = "forward:/registration/user/";
		
		if(user.isEmpty()) {
			return toRegistrationEcommerceUserController;
		}
		
		try {
			
			TicketDTO ticketDTO = userService.save(user);
			
			request.setAttribute("newTicket", ticketDTO);
			request.setAttribute("message", ofSuccess("Dados cadastrados com sucesso!"));
			
		} catch (Exception e) {

			request.setAttribute("user", user);
			request.setAttribute("message", ofError(e));
			
		}
		
		return toRegistrationEcommerceUserController;

	}

}