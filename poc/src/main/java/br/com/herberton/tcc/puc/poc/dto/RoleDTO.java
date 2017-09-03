package br.com.herberton.tcc.puc.poc.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

public class RoleDTO 
	implements IDTO<RoleDTO> {

	
	private static final long serialVersionUID = -1941158447382948556L;

	
	private Integer id;
	private String name;
	private RoleType type;
	private List<UserDTO> users;
	
	
	public Integer getId() {
		return id;
	}
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
	public List<UserDTO> getUsers() {
		if(users == null) {
			this.setUsers(new ArrayList<>());
		}
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof RoleDTO)) {
			return false;
		}
		
		RoleDTO other = (RoleDTO) object;
		
		return 
			new EqualsBuilder()
				.append(this.getName(), other.getName())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getName())
					.toHashCode();
	}
	
}
