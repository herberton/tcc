package br.com.herberton.tcc.puc.poc.business.registration.user;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.converter.UserEntity2LoginDTOConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.DefaultUserDTO;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=REQUIRED)
public abstract class DefaultUserRegistrationBusiness<T extends DefaultUserDTO<T>> {
	
	@Autowired
	protected IUserDAO userDAO;
	
	@Autowired
	protected IAuthenticationBusiness authenticationBusiness;
	
	@Autowired
	protected UserEntity2LoginDTOConverter userEntity2LoginDTOConverter;
	
	
	public abstract UserEntity create(T dto);
	public abstract UserEntity update(UserEntity entity, T dto);
	
	
	public TicketDTO save(T dto) {
		
		if(dto == null || dto.isEmpty()) {
			throw new IllegalArgumentException("Estamos com problemas, tente novamente mais tarde!");
		}
		
		UserEntity user = null;
		
		if(dto.getId() == null) {
			
			if(this.userDAO.contains("cpf", dto.getCpf())) {
				throw new IllegalArgumentException("Já existe um usuário cadastrado com esse CPF!");
			}
			
			if(this.userDAO.contains("login", dto.getLogin())) {
				throw new IllegalArgumentException("Já existe um usuário cadastrado com esse login!");
			}
			
			user = this.create(dto);
			
		} else {
			
			List<UserEntity> foundList = this.userDAO.find("id", dto.getId());
			
			if(foundList.isEmpty()) {
				throw new IllegalArgumentException("Não existe um usuário cadastrado com esse ID!");
			}
			
			UserEntity entity = foundList.get(0);
			
			if(!entity.getCpf().equals(dto.getCpf()) && this.userDAO.contains("cpf", dto.getCpf())) {
				throw new IllegalArgumentException("Já existe um usuário cadastrado com esse CPF!");
			}
			
			if(!entity.getLogin().equals(dto.getLogin()) && this.userDAO.contains("login", dto.getLogin())) {
				throw new IllegalArgumentException("Já existe um usuário cadastrado com esse login!");
			}
			
			user = this.update(entity, dto);
			
		}
		
		LoginDTO login = this.userEntity2LoginDTOConverter.convert(user);
		
		TicketDTO ticket = this.authenticationBusiness.login(login);
		
		return ticket;

	}
	
}