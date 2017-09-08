package br.com.herberton.tcc.puc.poc.dto.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;


public class ListEcommerceUserDTO implements Serializable {
	
	private static final long serialVersionUID = -9212803664594736240L;
	
	private List<EcommerceUserDTO> list;
	
	public List<EcommerceUserDTO> getList() {
		if(list == null) {
			setList(new ArrayList<>());
		}
		return list;
	}
	
	public void setList(List<EcommerceUserDTO> list) {
		this.list = list;
	}
	
}
