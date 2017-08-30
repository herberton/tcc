package br.com.herberton.tcc.puc.poc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class UserEntity extends DefaultEntity<Integer, UserEntity> {
	
	private static final long serialVersionUID = 6729939422058790598L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String cpf;
	
	@Column(nullable=false, unique=true)
	private String login;
	
	@Column
	private String password;
	
	@Column
	private String ticket;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false, insertable=false, updatable=false)
	private List<RoleEntity> roles;
	
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
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
	
	public List<RoleEntity> getRoles() {
		if(roles == null) {
			this.setRoles(new ArrayList<>());
		}
		return roles;
	}
	
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	
}