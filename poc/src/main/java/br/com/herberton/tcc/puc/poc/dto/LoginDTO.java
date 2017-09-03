package br.com.herberton.tcc.puc.poc.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;

public class LoginDTO 
	implements IDTO<LoginDTO> {
	
	private static final long serialVersionUID = -8316725737245529698L;
	
	private String login;
	private String password;
	
	
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
	
	
	public LoginDTO() {
		
	}
	
	public LoginDTO(LoggedUserDTO loggedUser) {
		if(loggedUser != null) {
			this.setLogin(loggedUser.getLogin());
			this.setPassword(loggedUser.getPassword());	
		}
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof LoginDTO)) {
			return false;
		}
		
		LoginDTO other = (LoginDTO) object;
		
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
