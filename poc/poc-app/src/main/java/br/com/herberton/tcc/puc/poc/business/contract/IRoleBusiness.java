package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.service.contract.IRoleService;

public interface IRoleBusiness 
	extends IRoleService {
	RoleEntity getAdministratorRole();
	RoleEntity getCustomerRole();
	RoleEntity getSpecialCustomerRole();
}