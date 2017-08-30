package br.com.herberton.tcc.puc.poc.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

public class UserDTO implements IDTO<UserDTO> {
	
	private static final long serialVersionUID = -8316725737245529698L;
	
	
	private String login;
	private String password;
	private List<RoleType> roles;
	private Boolean isAdmin;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleType> getRoles() {
		if(roles == null) {
			this.setRoles(new ArrayList<>());
		}
		return roles;
	}
	public void setRoles(List<RoleType> roles) {
		this.roles = roles;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof UserDTO)) {
			return false;
		}
		
		UserDTO other = (UserDTO) object;
		
		return 
			new EqualsBuilder()
				.append(this.getLogin(), other.getLogin())
				.append(this.getPassword(), other.getPassword())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getLogin())
				.append(this.getPassword())
					.toHashCode();
	}
	
}
