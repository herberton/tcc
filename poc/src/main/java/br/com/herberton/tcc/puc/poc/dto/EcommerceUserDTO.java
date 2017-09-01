package br.com.herberton.tcc.puc.poc.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;

public class EcommerceUserDTO 
	implements IDTO<EcommerceUserDTO> {

	private static final long serialVersionUID = -1941158447382948556L;

	private Integer id;
	
	private String cpf;
	
	private String login;
	
	private String password;
	
	private boolean specialCustomer;
	
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

	public boolean isSpecialCustomer() {
		return specialCustomer;
	}
	
	public void setSpecialCustomer(boolean specialCustomer) {
		this.specialCustomer = specialCustomer;
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof EcommerceUserDTO)) {
			return false;
		}
		
		EcommerceUserDTO other = (EcommerceUserDTO) object;
		
		return 
			new EqualsBuilder()
				.append(this.getId(), other.getId())
				.append(this.getLogin(), other.getCpf())
				.append(this.getPassword(), other.getLogin())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getId())
				.append(this.getLogin())
				.append(this.getCpf())
				.append(this.getLogin())
					.toHashCode();
	}
	
}
