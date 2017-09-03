package br.com.herberton.tcc.puc.poc.converter;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;

@Component
public class RoleEntity2RoleDTOConverter implements IConverter<RoleEntity, RoleDTO> {
	
	@Override
	public RoleDTO convert(RoleEntity entity) {
		
		RoleDTO dto = new RoleDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		
		return dto;
		
	}
	
}
