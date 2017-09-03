package br.com.herberton.tcc.puc.poc.dto.user;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

public class LoggedUserDTO 
	extends DefaultUserDTO<LoggedUserDTO> {
	
	private static final long serialVersionUID = -8316725737245529698L;
	
	
	private List<RoleType> roles;
	private Boolean isAdmin;
	private Boolean isSpecialCustomer;
	private Boolean isExternalPartner;
	private Boolean canAccessBackoffice;
	
	
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
	public Boolean getIsExternalPartner() {
		return isExternalPartner;
	}
	public void setIsExternalPartner(Boolean isExternalPartner) {
		this.isExternalPartner = isExternalPartner;
	}
	public Boolean getCanAccessBackoffice() {
		return canAccessBackoffice;
	}
	public void setCanAccessBackoffice(Boolean canAccessBackoffice) {
		this.canAccessBackoffice = canAccessBackoffice;
	}
	
	public LoggedUserDTO() {
	
	}
	
	public LoggedUserDTO(EcommerceUserDTO registrationUser) {
		if(registrationUser != null) {
			this.setLogin(registrationUser.getLogin());
			this.setPassword(registrationUser.getPassword());	
		}
	}
	
	
}
