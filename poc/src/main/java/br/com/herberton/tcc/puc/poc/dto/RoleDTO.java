package br.com.herberton.tcc.puc.poc.dto;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

public class RoleDTO 
	implements IDTO<RoleDTO> {

	private static final long serialVersionUID = -1333497766241786056L;

	
	private Integer id;
	
	private String name;
	
	private RoleType type;
	
	
	
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
	
}
