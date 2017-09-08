package br.com.herberton.tcc.puc.poc.dto.contract;

import java.io.Serializable;

public interface IDTO<T extends IDTO<T>> extends Serializable {
	
	default boolean isEmpty() {
		try {
			return this.equals(this.getClass().newInstance());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
