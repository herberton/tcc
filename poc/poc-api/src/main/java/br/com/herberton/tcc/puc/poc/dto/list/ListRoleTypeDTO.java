package br.com.herberton.tcc.puc.poc.dto.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.enumerator.RoleType;


public class ListRoleTypeDTO implements Serializable {
	
	private static final long serialVersionUID = 5277405763962538208L;
	
	private List<RoleType> list;
	
	public List<RoleType> getList() {
		if(list == null) {
			setList(new ArrayList<>());
		}
		return list;
	}
	
	public void setList(List<RoleType> list) {
		this.list = list;
	}
	
}
