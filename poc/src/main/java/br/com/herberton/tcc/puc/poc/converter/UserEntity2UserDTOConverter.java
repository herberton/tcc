package br.com.herberton.tcc.puc.poc.converter;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Component
public class UserEntity2UserDTOConverter implements IConverter<UserEntity, UserDTO> {
	
	@Override
	public UserDTO convert(UserEntity entity) {
		
		UserDTO dto = new UserDTO();
		
		dto.setId(entity.getId());
		dto.setCpf(entity.getCpf());
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		
		for (RoleEntity roleEntity : entity.getRoles()) {
			
			RoleDTO roleDTO = new RoleDTO();
			
			roleDTO.setId(roleEntity.getId());
			roleDTO.setName(roleEntity.getName());
			roleDTO.setType(roleEntity.getType());
		
			dto.getRoles().add(roleDTO);
		
		
		}
		
		return dto;
		
	}
	
}
