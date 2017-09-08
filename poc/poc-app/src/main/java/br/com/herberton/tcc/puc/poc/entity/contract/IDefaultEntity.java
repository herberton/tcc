package br.com.herberton.tcc.puc.poc.entity.contract;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;

public interface IDefaultEntity<K extends Serializable, V extends IDefaultEntity<K, V>> 
	extends Comparable<V>, IDTO<V> {
	
	K getId();
	
	void setId(K id);
	
	@Override
	default int compareTo(V other) {
		
		if(other == null) {
			return -1;
		}
		
		return 
			new CompareToBuilder()
				.append(this.getId(), other.getId())
					.toComparison();
	
	}
	
}
