package br.com.herberton.tcc.puc.poc.business.registration;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herberton.tcc.puc.poc.business.registration.contract.IRoleRegistrationBusiness;
import br.com.herberton.tcc.puc.poc.converter.RoleEntity2RoleDTOConverter;
import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;
import br.com.herberton.tcc.puc.poc.enumerator.RoleType;

@Service
@Transactional(propagation=REQUIRED)
public class RoleRegistrationBusiness 
	implements IRoleRegistrationBusiness {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private RoleEntity2RoleDTOConverter roleEntity2RoleDTOConverter;
	
	@Override
	public void save(RoleDTO dto) {
		
		if(dto == null || dto.isEmpty()) {
			throw new IllegalArgumentException("Estamos com problemas, tente novamente mais tarde!");
		}
		
		if(dto.getId() == null) {
			
			if(this.roleDAO.contains("name", dto.getName())) {
				throw new IllegalArgumentException("Já existe um papel cadastrado com esse nome!");
			}
			
			this.create(dto);
			
		} else {
			
			List<RoleEntity> foundList = this.roleDAO.find("id", dto.getId());
			
			if(foundList.isEmpty()) {
				throw new IllegalArgumentException("Não existe um papel cadastrado com esse ID!");
			}
			
			RoleEntity entity = foundList.get(0);
			
			if(!entity.getName().equals(dto.getName()) && this.roleDAO.contains("name", dto.getName())) {
				throw new IllegalArgumentException("Já existe um papel cadastrado com esse nome!");
			}
			
			this.update(entity, dto);
			
		}

	}
	
	public void create(RoleDTO dto) {
		
		RoleEntity entity = new RoleEntity();
		
		entity.setName(dto.getName());
		entity.setType(dto.getType());
		
		this.roleDAO.insert(entity);
		
	}
	
	public void update(RoleEntity entity, RoleDTO dto) {
		
		entity.setName(dto.getName());
		entity.setType(dto.getType());
		
		this.roleDAO.update(entity);
		
	}
	
	@Override
	public void delete(Integer id) {
		if(defaultIfNull(id, INTEGER_ZERO).equals(INTEGER_ZERO) || !this.roleDAO.contains(id)) {
			throw new IllegalArgumentException("Não foi possível excluir o papel selecionado, tente novamente mais tarde!");
		}
		this.roleDAO.delete(id);
	}
	
	@Override
	public RoleDTO find(Integer id) {
		
		if(defaultIfNull(id, INTEGER_ZERO).equals(INTEGER_ZERO) || !this.roleDAO.contains(id)) {
			throw new IllegalArgumentException("Não foi possível buscar o papel selecionado, tente novamente mais tarde!");
		}
		
		RoleEntity entity = this.roleDAO.find(id);
		
		return this.roleEntity2RoleDTOConverter.convert(entity);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<RoleDTO> listRoles() {
		
		List<RoleEntity> entityList = this.roleDAO.list();
		
		List<RoleDTO> dtoList = new ArrayList<>();
		
		for (RoleEntity entity : entityList) {
			dtoList.add(this.roleEntity2RoleDTOConverter.convert(entity));
		}
		
		return dtoList;
	}
	
	@Override
	public List<RoleType> listRoleTypes() {
		return asList(RoleType.values());
	}
	
}