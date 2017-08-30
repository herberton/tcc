package br.com.herberton.tcc.puc.poc.converter;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.converter.contract.IConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.dto.RegistrationUserDTO;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Component
public class RegistrationUserDTO2UserEntityConverter implements IConverter<RegistrationUserDTO, UserEntity> {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Override
	public UserEntity convert(RegistrationUserDTO dto) {
		
		UserEntity user = new UserEntity();
		
		user.setCpf(dto.getCpf());
		user.setLogin(dto.getLogin());
		user.setPassword(dto.getPassword());
		
		Iterator<RoleDTO> iterator = dto.getRoles().iterator();
		while (iterator.hasNext()) {
			RoleEntity role = this.roleDAO.find(iterator.next().getId());
			user.getRoles().add(role);
		}

		return user;
		
	}
	
}
