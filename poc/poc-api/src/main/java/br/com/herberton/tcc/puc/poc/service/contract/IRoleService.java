package br.com.herberton.tcc.puc.poc.service.contract;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleTypeDTO;

public interface IRoleService {
	String PATH = "/role";
	void save(RoleDTO dto);
	ListRoleDTO listRoles();
	ListRoleTypeDTO listRoleTypes();
	void delete(Integer id);
	RoleDTO find(Integer id);
}