package br.com.herberton.tcc.puc.poc.dto.user;

import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;

public class UserDTO 
	extends DefaultUserDTO<UserDTO> {
	
	private static final long serialVersionUID = -1941158447382948556L;
	
	
	private List<RoleDTO> roles;
	
	
	public List<RoleDTO> getRoles() {
		if(roles == null) {
			this.setRoles(new ArrayList<>());
		}
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
}
