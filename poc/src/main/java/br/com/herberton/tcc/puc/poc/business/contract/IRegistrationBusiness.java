package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.RegistrationUserDTO;

public interface IRegistrationBusiness {
	LoggedUserDTO save(RegistrationUserDTO dto);
}