package br.com.herberton.tcc.puc.poc.dto.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;

public abstract class DefaultUserDTO<T extends DefaultUserDTO<T>> 
	implements IDTO<T> {
	
	private static final long serialVersionUID = 865068597887879025L;
	
	
	private Integer id;
	private String cpf;
	private String login;
	private String password;
	
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
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof DefaultUserDTO)) {
			return false;
		}
		
		DefaultUserDTO<?> other = (DefaultUserDTO<?>) object;
		
		return 
			new EqualsBuilder()
				.append(this.getCpf(), other.getCpf())
				.append(this.getLogin(), other.getLogin())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getCpf())
				.append(this.getLogin())
					.toHashCode();
	}
}
