package br.com.herberton.tcc.puc.poc.converter;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Component
public class UserEntity2LoginDTOConverter implements IConverter<UserEntity, LoginDTO> {
	
	@Override
	public LoginDTO convert(UserEntity entity) {
		
		LoginDTO dto = new LoginDTO();
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		
		return dto;
		
	}
	
}
