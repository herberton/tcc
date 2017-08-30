package br.com.herberton.tcc.puc.poc.dao.contract;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.herberton.tcc.puc.poc.entity.contract.IDefaultEntity;

public interface IDAO<K extends Serializable, V extends IDefaultEntity<K, V>> {
	
	Class<V> getEntityClazz();
	
	EntityManager getEntityManager();
	
	default V find(K id) {
		V entity = this.getEntityManager().find(this.getEntityClazz(), id);
		return entity;
	}
	
	default List<V> find(String key, Object value) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(key, value);
		return this.find(parameters);
	}
	
	default List<V> find(Map<String, Object> parameters) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT entity ");
		jpql.append("FROM ").append(this.getEntityClazz().getSimpleName()).append(" AS entity "); 
		
		if(!defaultIfNull(parameters, new HashMap<>()).isEmpty()) {
			jpql.append("WHERE ");
			int i = 0;
			for (String key : parameters.keySet()) {
				if(i > 0) {
					jpql.append(" AND ");		
				}
				jpql.append("entity.").append(key).append(" = :").append(key);
				i++;
			}
		}
		
		TypedQuery<V> query = this.getEntityManager().createQuery(jpql.toString(), this.getEntityClazz());
		
		if(!defaultIfNull(parameters, new HashMap<>()).isEmpty()) {
			parameters.keySet().forEach(key -> query.setParameter(key, parameters.get(key)));
		}
		
		return query.getResultList();
		
	}
	
	default void insert(V entity) {
		this.getEntityManager().persist(entity);
	}
	
	default V update(V entity) {
		return this.getEntityManager().merge(entity);
	}
	
	default void delete(K id) {
		V entity = this.find(id);
		if(entity == null) {
			throw new IllegalArgumentException("id(" + id +") inválido");
		}
		this.getEntityManager().remove(entity);
	}
	
}