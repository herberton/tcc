package br.com.herberton.tcc.puc.poc.business.registration.contract;

import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

public interface IRoleRegistrationBusiness {
	void save(RoleDTO dto);
	List<RoleDTO> listRoles();
	List<RoleType> listRoleTypes();
	void delete(Integer id);
	RoleDTO find(Integer id);
}