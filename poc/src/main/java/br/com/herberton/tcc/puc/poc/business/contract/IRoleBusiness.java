package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.entity.RoleEntity;

public interface IRoleBusiness {
	RoleEntity getAdministratorRole();
	RoleEntity getCustomerRole();
	RoleEntity getSpecialCustomerRole();
}