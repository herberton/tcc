package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.entity.UserEntity;
import br.com.herberton.tcc.puc.poc.service.contract.IUserService;

public interface IUserBusiness 
	extends IUserService {
	UserEntity getAdministratorUser();
}