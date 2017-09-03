package br.com.herberton.tcc.puc.poc.helper;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.IUserHelper;

@Component
public class UserHelper implements IUserHelper {

	@Override
	public boolean contains(UserDTO user, RoleDTO role) {
		if(user == null || user.isEmpty() || role == null || 
			role.isEmpty() || role.getId() == null || role.getId() == 0) {
			return false;
		}
		return user.getRoles().stream().map(RoleDTO::getId).filter(id -> role.getId().equals(id)).count() > 0;
	}
	
}
