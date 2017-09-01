package br.com.herberton.tcc.puc.poc.converter;

import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.ADMINISTRATOR;
import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.SPECIAL_CUSTOMER;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Component
public class UserEntity2LoggedUserDTOConverter implements IConverter<UserEntity, LoggedUserDTO> {
	
	@Override
	public LoggedUserDTO convert(UserEntity entity) {
		
		LoggedUserDTO dto = new LoggedUserDTO();
		dto.setId(entity.getId());
		dto.setCpf(entity.getCpf());
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		
		for (RoleEntity role : entity.getRoles()) {
			dto.getRoles().add(role.getType());
		}
		
		dto.setIsAdmin(dto.getRoles().contains(ADMINISTRATOR));
		dto.setIsSpecialCustomer(dto.getRoles().contains(SPECIAL_CUSTOMER));
		
		return dto;
		
	}
	
}
