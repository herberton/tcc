package br.com.herberton.tcc.puc.poc.business;

import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.ADMINISTRATOR;
import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class LoginBusiness implements ILoginBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	
	@Override
	public String login(UserDTO dto) {
		
		dto = defaultIfNull(dto, new UserDTO());
		
		if(dto.isEmpty()) {
			return null;
		}
		
		final String login = defaultString(dto.getLogin()).trim();
		final String password = defaultString(dto.getPassword()).trim();
		
		if(login.isEmpty()) {
			return null;
		}
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", login);
		parameters.put("password", password);
		
		List<UserEntity> foundUsers = this.userDAO.find(parameters);
		
		UserEntity entity = null;
		
		if(foundUsers.isEmpty()) {
			
			if(!login.equals("admin") ||  !password.equals("admin")) {
				return null;	
			}
			
			List<RoleEntity> foundRoles = this.roleDAO.find("name", "admin");
			
			RoleEntity role = null;
			
			if(foundRoles.isEmpty()) {
				
				role = new RoleEntity();
				role.setName("admin");
				role.setType(ADMINISTRATOR);
				
				this.roleDAO.insert(role);
				
			} else {
				
				role = foundRoles.get(0);
				
			}
				
			entity = new UserEntity();
			entity.setLogin(login);
			entity.setPassword(password);
			entity.setTicket(this.newTicket());
			entity.getRoles().add(role);
			
			this.userDAO.insert(entity);
			
		} else {
			
			entity = foundUsers.get(0);
			
		}
		
		if (defaultString(entity.getTicket()).trim().isEmpty()) {
			
			entity.setTicket(this.newTicket());
			
			entity = this.userDAO.update(entity);
		
		}
		
		return entity.getTicket();
		
	}
	
	
	private String newTicket() {
		String ticket = randomUUID().toString().trim();
		return ticket;
	}


	@Override
	public void logout(String ticket) {

		ticket = defaultString(ticket).trim();
		
		if(ticket.isEmpty()) {
			return;
		}
		
		List<UserEntity> foundUsers = this.userDAO.find("ticket", ticket);
		
		if(!foundUsers.isEmpty()) {
			
			UserEntity entity = foundUsers.get(0);
			entity.setTicket(null);
			this.userDAO.update(entity);
			
		}
		
	}
	
	@Override
	public UserDTO getLoggedUser(String ticket) {
		
		ticket = defaultString(ticket).trim();
		
		if(ticket.isEmpty()) {
			return null;
		}
		
		List<UserEntity> foundUsers = this.userDAO.find("ticket", ticket);
		
		if(foundUsers.isEmpty()) {
			return null;
		}
		
		UserEntity entity = foundUsers.get(0);
		
		if (entity.getRoles().isEmpty() 
				&& entity.getLogin().equals("admin") 
				&& entity.getPassword().equals("admin")) {
			
			List<RoleEntity> foundRoles = this.roleDAO.find("name", "admin");
			
			RoleEntity role = null;
			
			if(foundRoles.isEmpty()) {
				
				role = new RoleEntity();
				role.setName("admin");
				role.setType(ADMINISTRATOR);
				
				this.roleDAO.insert(role);
				
			} else {
				
				role = foundRoles.get(0);
				
			}
			
			entity.getRoles().add(role);
			entity = this.userDAO.update(entity);
			
		}
		
		UserDTO dto = new UserDTO();
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		
		for (RoleEntity role : entity.getRoles()) {
			dto.getRoles().add(role.getType());
		}
		
		dto.setIsAdmin(dto.getRoles().contains(ADMINISTRATOR));
		
		return dto;
		
	}
	
}