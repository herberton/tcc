package br.com.herberton.tcc.puc.poc.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	
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
	
}
