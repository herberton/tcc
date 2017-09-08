package br.com.herberton.tcc.puc.poc.service;

import static br.com.herberton.tcc.puc.poc.service.contract.IRoleService.PATH;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleTypeDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IRoleService;

@RestController
@RequestMapping(PATH)
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleBusiness roleBusiness;

	@Override
	@RequestMapping(method = GET)
	public ListRoleDTO listRoles() {
		ListRoleDTO roles = this.roleBusiness.listRoles();
		return roles;
	}

	@Override
	@RequestMapping(path="/{id}", method = GET)
	public RoleDTO find(@PathVariable Integer id) {
		RoleDTO role = this.roleBusiness.find(id);
		return role;
	}
	
	@Override
	@RequestMapping(method = PUT)
	@ResponseStatus(value = NO_CONTENT)
	public void save(@RequestBody RoleDTO role) {
		this.roleBusiness.save(role);
	}

	@Override
	@RequestMapping(path="/{id}", method = DELETE)
	@ResponseStatus(value = NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		this.roleBusiness.delete(id);
	}
	
	
	@Override
	@RequestMapping(path="/type", method = GET)
	public ListRoleTypeDTO listRoleTypes() {
		ListRoleTypeDTO types = this.roleBusiness.listRoleTypes();
		return types;
	}
	
}