package br.com.herberton.tcc.puc.poc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.herberton.tcc.puc.poc.dao.contract.IRoleDAO;
import br.com.herberton.tcc.puc.poc.entity.RoleEntity;

@Repository
public class RoleDAO implements IRoleDAO {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Class<RoleEntity> getEntityClazz() {
		return RoleEntity.class;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
}
