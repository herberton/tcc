package br.com.herberton.tcc.puc.poc.business;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class LoginBusiness implements ILoginBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	
	@Override
	public String login(UserDTO dto) {
		
		if(dto == null) {
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
		
		List<UserEntity> foundList = this.userDAO.find(parameters);
		
		UserEntity entity = null;
		
		if(foundList.isEmpty()) {
			
			if(!login.equals("admin") ||  !password.equals("admin")) {
				return null;	
			}
			
			entity = new UserEntity();
			entity.setLogin(login);
			entity.setPassword(password);
			entity.setTicket(this.newTicket());
			
			this.userDAO.insert(entity);
			
		} else {
			
			entity = foundList.get(0);
			
		}
		
		
		if (defaultString(entity.getTicket()).trim().isEmpty()) {
			
			entity.setTicket(this.newTicket());
			
			entity = this.userDAO.update(entity);
		
		}
		
		return entity.getTicket();
		
	}
	
	
	private String newTicket() {
		String ticket = randomUUID().toString();;
		return ticket;
	}


	@Override
	public void removeLoggedUser(String ticket) {

		if(ticket == null) {
			return;
		}
		
		List<UserEntity> foundList = this.userDAO.find("ticket", ticket);
		
		if(!foundList.isEmpty()) {
			
			UserEntity entity = foundList.get(0);
			entity.setTicket(null);
			this.userDAO.update(entity);
			
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserDTO getLoggedUser(String ticket) {
		
		if(ticket == null) {
			return null;
		}
		
		List<UserEntity> foundList = this.userDAO.find("ticket", ticket);
		
		if(foundList.isEmpty()) {
			return null;
		}
		
		UserEntity entity = foundList.get(0);
		
		UserDTO user = new UserDTO();
		user.setLogin(entity.getLogin());
		user.setPassword(entity.getPassword());
		
		return user;
		
	}
	
}