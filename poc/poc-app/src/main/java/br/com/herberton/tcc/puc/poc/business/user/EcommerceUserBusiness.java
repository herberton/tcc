package br.com.herberton.tcc.puc.poc.business.user;

import static br.com.herberton.tcc.puc.poc.enumerator.RoleType.SPECIAL_CUSTOMER;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.contract.IEcommerceUserBusiness;
import br.com.herberton.tcc.puc.poc.business.contract.IRoleBusiness;
import br.com.herberton.tcc.puc.poc.dto.list.ListEcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Service
@Transactional(propagation=REQUIRED)
public class EcommerceUserBusiness 
	extends DefaultUserBusiness<EcommerceUserDTO>
	implements IEcommerceUserBusiness {
	
	
	@Autowired
	private IRoleBusiness roleBusiness;
	
	
	@Override
	public UserEntity create(EcommerceUserDTO dto) {
		
		UserEntity entity = new UserEntity();
		
		entity.setCpf(dto.getCpf());
		entity.setLogin(dto.getLogin());
		entity.setPassword(dto.getPassword());
		
		RoleEntity customerRole = this.roleBusiness.getCustomerRole();
		
		entity.getRoles().add(customerRole);
		
		if(dto.isSpecialCustomer()) {
			
			RoleEntity specialCustomerRole = this.roleBusiness.getSpecialCustomerRole();
			
			entity.getRoles().add(specialCustomerRole);
			
		}
		
		this.userDAO.insert(entity);
		
		return entity;
		
	}
	
	@Override
	public UserEntity update(UserEntity entity, EcommerceUserDTO dto) {
		
		entity.setCpf(dto.getCpf());
		entity.setLogin(dto.getLogin());
		entity.setPassword(dto.getPassword());
		
		if(entity.isSpecialCustomer() != dto.isSpecialCustomer()) {
			
			if(dto.isSpecialCustomer()) {
				
				RoleEntity role = this.roleBusiness.getSpecialCustomerRole();
				
				entity.getRoles().add(role);
				
			} else {
				
				Iterator<RoleEntity> iterator = entity.getRoles().iterator();
				
				while (iterator.hasNext()) {
					
					RoleEntity role = iterator.next();
					
					if(role.getType().equals(SPECIAL_CUSTOMER)) {
					
						iterator.remove();
				
					}
					
				}
				
			}
			
		}
		
		entity = this.userDAO.update(entity);
		
		return entity;
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public ListEcommerceUserDTO list() {
		
		List<UserEntity> entityList = this.userDAO.list();
		
		List<EcommerceUserDTO> dtoList = new ArrayList<>();
		
		for (UserEntity entity : entityList) {
			
			EcommerceUserDTO dto = new EcommerceUserDTO();
			
			dto.setId(entity.getId());
			dto.setCpf(entity.getCpf());
			dto.setLogin(entity.getLogin());
			dto.setPassword(entity.getPassword());
			dto.setSpecialCustomer(entity.isSpecialCustomer());
			
			dtoList.add(dto);
		}
		
		ListEcommerceUserDTO list = new ListEcommerceUserDTO();
		list.setList(dtoList);
		
		return list;
		
	}
	
}