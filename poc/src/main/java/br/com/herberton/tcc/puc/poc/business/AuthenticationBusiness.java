package br.com.herberton.tcc.puc.poc.business;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IUserBusiness;
import br.com.herberton.tcc.puc.poc.converter.UserEntity2LoggedUserDTOConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=REQUIRED)
public class AuthenticationBusiness implements IAuthenticationBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IUserBusiness userBusiness;
	
	
	@Autowired
	private UserEntity2LoggedUserDTOConverter userEntity2LoggedUserDTOConverter;
	
	
	@Override
	public TicketDTO login(LoginDTO dto) {
		
		dto = defaultIfNull(dto, new LoginDTO());
		
		if(dto.isEmpty()) {
			throw new IllegalArgumentException("Estamos com problemas, tente novamente mais tarde!");
		}
		
		final String login = defaultString(dto.getLogin()).trim();
		final String password = defaultString(dto.getPassword()).trim();
		
		if(login.isEmpty()) {
			throw new IllegalArgumentException("O campo login deve ser preenchido!");
		}
		
		
		UserEntity entity = null;
		
		
		if(login.equals("admin") && password.equals("admin")) {
			
			entity = this.userBusiness.getAdministratorUser();
		
		} else {
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("login", login);
			parameters.put("password", password);
			
			List<UserEntity> foundUsers = this.userDAO.find(parameters);
			
			if(foundUsers.isEmpty()) {
				throw new IllegalArgumentException("Login e/ou senha inválido(s)!");
			}
			
			entity = foundUsers.get(0);
			
		}
		
		if (entity.getRoles().isEmpty()) {
			throw new IllegalArgumentException("Usuário não possui permissões, entre em contato com o administrador do sistema!");
		}
		
		if (defaultString(entity.getTicket()).trim().isEmpty()) {
			
			entity.setTicket(this.newTicket());
			
			entity = this.userDAO.update(entity);
		
		}
		
		String ticket = entity.getTicket();
		
		return new TicketDTO(ticket);
		
	}
	
	private String newTicket() {
		String ticket = randomUUID().toString().trim();
		return ticket;
	}


	@Override
	public void logout(TicketDTO ticket) {

		if(ticket.isEmpty()) {
			return;
		}
		
		List<UserEntity> foundUsers = this.userDAO.find("ticket", ticket.getValue());
		
		if(!foundUsers.isEmpty()) {
			
			UserEntity entity = foundUsers.get(0);
			
			entity.setTicket(null);
			
			this.userDAO.update(entity);
			
		}
		
	}
	
	
	@Override
	public LoggedUserDTO getLoggedUser(TicketDTO ticket) {
		
		if(ticket.isEmpty()) {
			return null;
		}
		
		List<UserEntity> foundUsers = this.userDAO.find("ticket", ticket.getValue());
		
		if(foundUsers.isEmpty()) {
			return null;
		}
		
		UserEntity entity = foundUsers.get(0);
		
		LoggedUserDTO loggeUser = this.userEntity2LoggedUserDTOConverter.convert(entity);
		
		return loggeUser;
		
	}
	
	
}