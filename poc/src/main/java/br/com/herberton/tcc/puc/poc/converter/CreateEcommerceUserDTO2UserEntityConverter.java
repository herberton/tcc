package br.com.herberton.tcc.puc.poc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dto.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Component
public class CreateEcommerceUserDTO2UserEntityConverter implements IConverter<EcommerceUserDTO, UserEntity> {
	
	@Autowired
	private IRoleBusiness roleBusiness;
	
	@Override
	public UserEntity convert(EcommerceUserDTO dto) {
		
		UserEntity user = new UserEntity();
		
		user.setCpf(dto.getCpf());
		user.setLogin(dto.getLogin());
		user.setPassword(dto.getPassword());
		
		RoleEntity customerRole = this.roleBusiness.getCustomerRole();
		
		user.getRoles().add(customerRole);
		
		if(dto.isSpecialCustomer()) {
			
			RoleEntity specialCustomerRole = this.roleBusiness.getSpecialCustomerRole();
			
			user.getRoles().add(specialCustomerRole);
			
		}
		
		return user;
		
	}
	
	
	
}
