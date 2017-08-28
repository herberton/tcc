package br.com.herberton.tcc.puc.poc.business;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.List;

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
		
		if(defaultString(dto.getLogin()).equals("admin") && defaultString(dto.getPassword()).equals("admin")) {
		
			List<UserEntity> foundList = this.userDAO.find("login", dto.getLogin());
			
			UserEntity entity = null;
			
			if (foundList.isEmpty()) {
				
				entity = new UserEntity();
				entity.setLogin(dto.getLogin());
				entity.setPassword(dto.getPassword());
				
				this.userDAO.insert(entity);
				
			} else {
				
				entity = foundList.get(0);
				
			}
			
			String ticket = randomUUID().toString();
			
			entity.setTicket(ticket);
			
			entity = this.userDAO.update(entity);
			
			return ticket;
			
		}
		
		return null;
		
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