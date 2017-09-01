package br.com.herberton.tcc.puc.poc.dto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

public class LoggedUserDTO implements IDTO<LoggedUserDTO> {
	
	private static final long serialVersionUID = -8316725737245529698L;
	
	
	private Integer id;
	private String cpf;
	private String login;
	private String password;
	private List<RoleType> roles;
	private Boolean isAdmin;
	private Boolean isSpecialCustomer;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
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
		return defaultIfNull(isAdmin, false);
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Boolean getIsSpecialCustomer() {
		return defaultIfNull(isSpecialCustomer, false);
	}
	public void setIsSpecialCustomer(Boolean isSpecialCustomer) {
		this.isSpecialCustomer = isSpecialCustomer;
	}
	
	public LoggedUserDTO() {
	
	}
	
	public LoggedUserDTO(EcommerceUserDTO registrationUser) {
		if(registrationUser != null) {
			this.setLogin(registrationUser.getLogin());
			this.setPassword(registrationUser.getPassword());	
		}
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof LoggedUserDTO)) {
			return false;
		}
		
		LoggedUserDTO other = (LoggedUserDTO) object;
		
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
