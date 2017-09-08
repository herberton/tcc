package br.com.herberton.tcc.puc.poc.dto.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;


public class ListRoleDTO implements Serializable {
	
	private static final long serialVersionUID = 4776583435927881946L;
	
	private List<RoleDTO> list;
	
	public List<RoleDTO> getList() {
		if(list == null) {
			setList(new ArrayList<>());
		}
		return list;
	}
	
	public void setList(List<RoleDTO> list) {
		this.list = list;
	}
	
}
