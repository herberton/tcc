package br.com.herberton.tcc.puc.poc.service;

import static br.com.herberton.tcc.puc.poc.service.contract.IUserService.PATH;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.herberton.tcc.puc.poc.business.contract.IUserBusiness;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IUserService;

@RestController
@RequestMapping(PATH)
public class UserService implements IUserService {
	
	@Autowired
	private IUserBusiness userBusiness;

	@Override
	@RequestMapping(method = GET)
	public ListUserDTO listUsers() {
		ListUserDTO users = this.userBusiness.listUsers();
		return users;
	}

	@Override
	@RequestMapping(path="/{id}", method = GET)
	public UserDTO find(@PathVariable Integer id) {
		UserDTO user = this.userBusiness.find(id);
		return user;
	}
	
	@Override
	@RequestMapping(method = POST)
	public TicketDTO save(@RequestBody UserDTO user) {
		TicketDTO ticket = this.userBusiness.save(user);
		return ticket;
	}

	@Override
	@RequestMapping(path="/{id}", method = DELETE)
	@ResponseStatus(value = NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		this.userBusiness.delete(id);
	}
	
	
	@Override
	@RequestMapping(path="/roles", method = GET)
	public ListRoleDTO listRoles() {
		ListRoleDTO roles = this.userBusiness.listRoles();
		return roles;
	}
	
}