package br.com.herberton.tcc.puc.poc.controller.registration;

import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofError;
import static br.com.herberton.tcc.puc.poc.dto.MessageDTO.ofSuccess;
import static br.com.herberton.tcc.puc.poc.dto.TicketDTO.withTicket;
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

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.business.registration.contract.IRoleRegistrationBusiness;
import br.com.herberton.tcc.puc.poc.dto.MessageDTO;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
@RequestMapping("/registration/role")
public class RoleController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private IAuthenticationBusiness loginBusiness;
	
	@Autowired
	private IRoleRegistrationBusiness registrationBusiness;
	
	
	@RequestMapping("/")
	public String role(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		LoggedUserDTO loggedUser = loginBusiness.getLoggedUser(withTicket(ticket));
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../index"; // toIndexController
		}
		
		String toFormController = "forward:/registration/role/form";
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		RoleDTO role = (RoleDTO)request.getAttribute("role");
		
		if(loggedUser.getIsAdmin()) {
			
			if(message!= null && message.getIsError() && role != null) {
				return toFormController;
			}
			
			
			String networkAddress = networkHelper.getNetworkAddress();
			List<RoleDTO> list = this.registrationBusiness.listRoles(); 
			
			model.addAttribute("message", message);
			model.addAttribute("loggedUser", loggedUser);
			model.addAttribute("networkAddress", networkAddress);
			model.addAttribute("list", list);
			
			return "registration/role/list"; // toListPage
		
		}
		
		return toFormController;
		
	}
	
	@RequestMapping("/delete")
	public String delete(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request) {
		
		LoggedUserDTO loggedUser = loginBusiness.getLoggedUser(withTicket(ticket));
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../index"; // toIndexController
		}
		
		int id = parseInt(defaultString(request.getParameter("id"), "0"));
		
		if(loggedUser.getIsAdmin() && id != 0) {
			
			try {
				
				this.registrationBusiness.delete(id);
				
				request.setAttribute("message", ofSuccess("Registro excluido com sucesso!"));
					
			} catch (Exception e) {
				
				request.setAttribute("message", ofError(e));
			
			}
			
		}
		
		return "forward:/registration/role/"; // toListController
		
	}
	
	@RequestMapping("/form")
	public String form(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request,  Model model) {
		
		LoggedUserDTO loggedUser = loginBusiness.getLoggedUser(withTicket(ticket));
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../index"; // toIndexController
		}
		
		String networkAddress = networkHelper.getNetworkAddress();
		List<RoleType> types = this.registrationBusiness.listRoleTypes();
		
		MessageDTO message = (MessageDTO)request.getAttribute("message");
		RoleDTO role = (RoleDTO)request.getAttribute("role");
		
		if(role == null) {
			int id = parseInt(defaultString(request.getParameter("id"), "0"));
			role = id == 0 ? null : this.registrationBusiness.find(id);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("role", role);
		model.addAttribute("types", types);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("networkAddress", networkAddress);
		
		return "registration/role/form"; // toFormPage
		
	}
	
	@RequestMapping("/form/save")
	public String save(@CookieValue(name=TICKET_COOKIE_NAME, required=false) String ticket, HttpServletRequest request, RoleDTO role) {
		
		LoggedUserDTO loggedUser = loginBusiness.getLoggedUser(withTicket(ticket));
		
		if(loggedUser == null || !loggedUser.getCanAccessBackoffice()) {
			return "redirect:../../../index"; // toIndexController
		}
		
		String toRoleController = "forward:/registration/role/";
		
		if(role.isEmpty()) {
			return toRoleController;
		}
		
		try {
			
			registrationBusiness.save(role);
			
			request.setAttribute("message", ofSuccess("Dados cadastrados com sucesso!"));
			
		} catch (Exception e) {

			request.setAttribute("role", role);
			request.setAttribute("message", ofError(e));
			
		}
		
		return toRoleController;

	}

}