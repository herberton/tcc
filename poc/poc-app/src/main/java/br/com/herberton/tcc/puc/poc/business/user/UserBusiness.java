package br.com.herberton.tcc.puc.poc.business.user;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IUserBusiness;
import br.com.herberton.tcc.puc.poc.converter.UserEntity2UserDTOConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=REQUIRED)
public class UserBusiness 
	extends DefaultUserBusiness<UserDTO>
	implements IUserBusiness {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IRoleBusiness roleBusiness;
	
	@Autowired
	private UserEntity2UserDTOConverter userEntity2UserDTOConverter;
	
	
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
	
	
	@Override
	public UserEntity create(UserDTO dto) {
		
		UserEntity entity = new UserEntity();
		
		entity.setCpf(dto.getCpf());
		entity.setLogin(dto.getLogin());
		entity.setPassword(dto.getPassword());
		
		for (RoleDTO role : dto.getRoles()) {
			if(this.roleDAO.contains(role.getId())) {
				entity.getRoles().add(this.roleDAO.find(role.getId()));
			}
		}
		
		this.userDAO.insert(entity);
		
		return entity;
		
	}
	
	@Override
	public UserEntity update(UserEntity entity, UserDTO dto) {
		
		entity.setCpf(dto.getCpf());
		entity.setLogin(dto.getLogin());
		entity.setPassword(dto.getPassword());
		
		entity.getRoles().clear();
		
		for (RoleDTO role : dto.getRoles()) {
			if(this.roleDAO.contains(role.getId())) {
				entity.getRoles().add(this.roleDAO.find(role.getId()));
			}
		}
		
		entity = this.userDAO.update(entity);
		
		return entity;
		
	}
	
	@Override
	public void delete(Integer id) {
		if(defaultIfNull(id, INTEGER_ZERO).equals(INTEGER_ZERO) || !this.userDAO.contains(id)) {
			throw new IllegalArgumentException("Não foi possível excluir o usuário selecionado, tente novamente mais tarde!");
		}
		this.userDAO.delete(id);
	}
	
	@Override
	public UserDTO find(Integer id) {
		
		if(defaultIfNull(id, INTEGER_ZERO).equals(INTEGER_ZERO) || !this.userDAO.contains(id)) {
			throw new IllegalArgumentException("Não foi possível buscar o usuário selecionado, tente novamente mais tarde!");
		}
		
		UserEntity entity = this.userDAO.find(id);
		
		return this.userEntity2UserDTOConverter.convert(entity);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public ListUserDTO listUsers() {
		
		List<UserEntity> entityList = this.userDAO.list();
		
		List<UserDTO> dtoList = new ArrayList<>();
		
		for (UserEntity entity : entityList) {
			dtoList.add(this.userEntity2UserDTOConverter.convert(entity));
		}
		
		ListUserDTO list = new ListUserDTO();
		list.setList(dtoList);
		
		return list;
	
	}
	
	@Override
	public ListRoleDTO listRoles() {
		
		List<RoleEntity> entityList = this.roleDAO.list();
		
		List<RoleDTO> dtoList = new ArrayList<>();
		
		for (RoleEntity roleEntity : entityList) {
			
			RoleDTO dto = new RoleDTO();
			
			dto.setId(roleEntity.getId());
			dto.setName(roleEntity.getName());
			dto.setType(roleEntity.getType());
			
			dtoList.add(dto);
			
		}
		
		ListRoleDTO list = new ListRoleDTO();
		list.setList(dtoList);
		
		return list;
	}
	
}