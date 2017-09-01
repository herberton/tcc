package br.com.herberton.tcc.puc.poc.business;

import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.SPECIAL_CUSTOMER;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IRegistrationBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.converter.CreateEcommerceUserDTO2UserEntityConverter;
import br.com.herberton.tcc.puc.poc.converter.UserEntity2LoginDTOConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=REQUIRED)
public class RegistrationBusiness implements IRegistrationBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IAuthenticationBusiness authenticationBusiness;
	
	@Autowired
	private IRoleBusiness roleBusiness;
	
	@Autowired
	private UserEntity2LoginDTOConverter userEntity2LoginDTOConverter;
	
	@Autowired
	private CreateEcommerceUserDTO2UserEntityConverter createEcommerceUserDTO2UserEntityConverter;
	
	
	@Override
	public TicketDTO save(EcommerceUserDTO dto) {
		
		dto = defaultIfNull(dto, new EcommerceUserDTO());
		
		if(dto.isEmpty()) {
			throw new IllegalArgumentException("Estamos com problemas, tente novamente mais tarde!");
		}
		
		UserEntity user = null;
		
		if(dto.getId() == null) {
			
			user = this.create(dto);
			
		} else {
			
			user = this.update(dto);
			
		}
		
		LoginDTO login = this.userEntity2LoginDTOConverter.convert(user);
		
		return this.authenticationBusiness.login(login);
		
	}


	public UserEntity create(EcommerceUserDTO dto) {
		
		if(this.userDAO.contains("cpf", dto.getCpf())) {
			throw new IllegalArgumentException("Já existe um usuário cadastrado com esse CPF!");
		}
		
		if(this.userDAO.contains("login", dto.getLogin())) {
			throw new IllegalArgumentException("Já existe um usuário cadastrado com esse login!");
		}
		
		UserEntity user = this.createEcommerceUserDTO2UserEntityConverter.convert(dto);
		
		this.userDAO.insert(user);
		
		return user;
		
	}
	
	
	public UserEntity update(EcommerceUserDTO dto) {
		
		List<UserEntity> foundUsers = this.userDAO.find("id", dto.getId());
		
		
		if(foundUsers.isEmpty()) {
			throw new IllegalArgumentException("Não existe um usuário cadastrado com esse ID!");
		}
		
		
		UserEntity user = foundUsers.get(0);
		
		
		user.setPassword(dto.getPassword());
		
		if(user.isSpecialCustomer() != dto.isSpecialCustomer()) {
			
			if(dto.isSpecialCustomer()) {
				
				RoleEntity role = roleBusiness.getSpecialCustomerRole();
				
				user.getRoles().add(role);
				
			} else {
				
				Iterator<RoleEntity> iterator = user.getRoles().iterator();
				
				while (iterator.hasNext()) {
					
					RoleEntity role = iterator.next();
					
					if(role.getType().equals(SPECIAL_CUSTOMER)) {
					
						iterator.remove();
				
					}
					
				}
				
			}
			
		}
		
		
		user = this.userDAO.update(user);
		
		return user;
		
	}
}