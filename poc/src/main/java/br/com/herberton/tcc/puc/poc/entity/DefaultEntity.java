package br.com.herberton.tcc.puc.poc.entity;

import java.io.Serializable;

public interface DefaultEntity<T extends Serializable> extends Serializable {
	T getId();
	void setId(T id);
}
