package br.com.herberton.tcc.puc.poc.converter;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;

@Component
public class LoggedUserDTO2EcommerceUserDTOConverter implements IConverter<LoggedUserDTO, EcommerceUserDTO> {
	
	@Override
	public EcommerceUserDTO convert(LoggedUserDTO loggedUser) {
		
		if(loggedUser == null) {
			return null;
		}
		
		EcommerceUserDTO ecommerceUser = new EcommerceUserDTO();
		
		ecommerceUser.setId(loggedUser.getId());
		ecommerceUser.setCpf(loggedUser.getCpf());
		ecommerceUser.setLogin(loggedUser.getLogin());
		ecommerceUser.setPassword(loggedUser.getPassword());
		
		ecommerceUser.setSpecialCustomer(loggedUser.getIsSpecialCustomer());
		
		return ecommerceUser;
		
	}
	
}
