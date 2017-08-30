package br.com.herberton.tcc.puc.poc.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;

public class RegistrationUserDTO 
	implements IDTO<RegistrationUserDTO> {

	private static final long serialVersionUID = -1941158447382948556L;

	
	private Integer id;
	
	private String cpf;
	
	private String login;
	
	private String password;
	
	private String ticket;
	
	private List<RoleDTO> roles;
	
	
	
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

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public List<RoleDTO> getRoles() {
		if(roles == null) {
			setRoles(new ArrayList<>());
		}
		return roles;
	}
	
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
}
