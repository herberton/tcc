package br.com.herberton.tcc.puc.poc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

@Entity
public class RoleEntity extends DefaultEntity<Integer, RoleEntity> {
	
	private static final long serialVersionUID = 6729939422058790598L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private RoleType type;
	
	@ManyToMany(mappedBy="roles")
	private List<UserEntity> users;
	
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}

	public List<UserEntity> getUsers() {
		if(users == null) {
			this.setUsers(new ArrayList<>());
		}
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
}