package br.com.herberton.tcc.puc.poc.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.entity.contract.IDefaultEntity;

@MappedSuperclass
public abstract class DefaultEntity<K extends Serializable, V extends DefaultEntity<K, V>>
	implements IDefaultEntity<K, V> {

	private static final long serialVersionUID = -5415644930839387162L;

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object object) {
		
		if (object == null) {
			return false;
		}
		
		if(!this.getClass().isAssignableFrom(object.getClass())) {
			return false;
		}
		
		return 
			new EqualsBuilder()
				.append(this.getId(), ((V)object).getId())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getId())
					.toHashCode();
	}
}
