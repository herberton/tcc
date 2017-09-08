package br.com.herberton.tcc.puc.poc.dto.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;


public class ListUserDTO implements Serializable {
	
	private static final long serialVersionUID = 979181344415164177L;
	
	private List<UserDTO> list;
	
	public List<UserDTO> getList() {
		if(list == null) {
			setList(new ArrayList<>());
		}
		return list;
	}
	
	public void setList(List<UserDTO> list) {
		this.list = list;
	}
	
}
