package br.com.herberton.tcc.puc.poc.business;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IUserBusiness;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=REQUIRED)
public class UserBusiness implements IUserBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IRoleBusiness roleBusiness;
	
	
	@Override
	public UserEntity getAdministratorUser() {

		final String cpf = "00000000000";
		final String login = "admin";
		final String password = "admin";
		
		List<UserEntity> foundList = this.userDAO.find("login", login);
		
		UserEntity user = null;
		
		if(foundList.isEmpty()) {
			
			user = new UserEntity();
			user.setCpf(cpf);
			user.setLogin(login);
			user.setPassword(password);
			
			RoleEntity role = roleBusiness.getAdministratorRole();
			user.getRoles().add(role);
			
			this.userDAO.insert(user);
			
		} else {
			
			user = foundList.get(0);
		
		}
		
		return user;
		
	}
	
}