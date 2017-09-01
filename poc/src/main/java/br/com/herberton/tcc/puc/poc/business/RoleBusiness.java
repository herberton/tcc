package br.com.herberton.tcc.puc.poc.business;

import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.ADMINISTRATOR;
import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.CUSTOMER;
import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.SPECIAL_CUSTOMER;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

@Service
@Transactional(propagation=REQUIRED)
public class RoleBusiness implements IRoleBusiness {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	
	@Override
	public RoleEntity getAdministratorRole() {
		return this.getRole("admin", ADMINISTRATOR);
	}
	
	@Override
	public RoleEntity getCustomerRole() {
		return this.getRole("customer", CUSTOMER);
	}
	
	@Override
	public RoleEntity getSpecialCustomerRole() {
		return this.getRole("specialCustomer", SPECIAL_CUSTOMER);
	}
	
	
	public RoleEntity getRole(String name, RoleType type) {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		parameters.put("type", type);
		
		List<RoleEntity> foundRoles = this.roleDAO.find(parameters);
		
		RoleEntity role = null;
		
		if(foundRoles.isEmpty()) {
			
			role = new RoleEntity();
			role.setName(name);
			role.setType(type);
			
			this.roleDAO.insert(role);
			
		} else {
			
			role = foundRoles.get(0);
		
		}
		
		return role;
		
	}
}