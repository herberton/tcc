package br.com.herberton.tcc.puc.poc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;

@Component
public class RoleEntity2RoleDTOConverter implements IConverter<RoleEntity, RoleDTO> {
	
	@Autowired
	private IRoleBusiness roleBusiness;
	
	@Override
	public RoleDTO convert(RoleEntity entity) {
		
		RoleDTO dto = new RoleDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		
		RoleEntity administratorRole = roleBusiness.getAdministratorRole();
		RoleEntity customerRole = roleBusiness.getCustomerRole();
		RoleEntity specialCustomerRole = roleBusiness.getSpecialCustomerRole();
		
		boolean canEdit = 
			!entity.equals(administratorRole) &&
			!entity.equals(customerRole) &&
			!entity.equals(specialCustomerRole);
		
		dto.setCanEdit(canEdit);
		
		return dto;
		
	}
	
}
