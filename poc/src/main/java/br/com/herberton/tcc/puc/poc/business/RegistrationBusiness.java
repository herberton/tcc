package br.com.herberton.tcc.puc.poc.business;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IRegistrationBusiness;
import br.com.herberton.tcc.puc.poc.converter.RegistrationUserDTO2UserEntityConverter;
import br.com.herberton.tcc.puc.poc.converter.UserEntity2LoggedUserDTOConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.RegistrationUserDTO;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class RegistrationBusiness implements IRegistrationBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private UserEntity2LoggedUserDTOConverter userEntity2LoggedUserDTOConverter;
	
	@Autowired
	private RegistrationUserDTO2UserEntityConverter registrationUserDTO2UserEntityConverter;
	
	
	@Override
	public LoggedUserDTO save(RegistrationUserDTO dto) {
		
		dto = defaultIfNull(dto, new RegistrationUserDTO());
		
		if(dto.isEmpty()) {
			return null;
		}
		
		boolean isInsert = defaultIfNull(dto.getId(), 0).equals(INTEGER_ZERO);
		
		if(!isInsert) {
			isInsert = this.userDAO.count("cpf", dto.getCpf()) <= 0;
		}
		
		UserEntity user = null;
		
		if(isInsert) {
			
			user = this.registrationUserDTO2UserEntityConverter.convert(dto);
			
			this.userDAO.insert(user);
			
		} else {
			
			user = this.userDAO.find(dto.getId());
			user.setPassword(dto.getPassword());
			
			Iterator<RoleDTO> iterator = dto.getRoles().iterator();
			while (iterator.hasNext()) {
				RoleEntity role = this.roleDAO.find(iterator.next().getId());
				user.getRoles().add(role);
			}
			
			this.userDAO.update(user);
			
		}
		
		LoggedUserDTO loggedUser = this.userEntity2LoggedUserDTOConverter.convert(user);
		
		return loggedUser;
		
	}
	
}