package br.com.herberton.tcc.puc.poc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.herberton.tcc.puc.poc.dao.contract.IUserDAO;
import br.com.herberton.tcc.puc.poc.entity.UserEntity;

@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Class<UserEntity> getEntityClazz() {
		return UserEntity.class;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
}
