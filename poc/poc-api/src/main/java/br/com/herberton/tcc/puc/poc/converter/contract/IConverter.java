package br.com.herberton.tcc.puc.poc.converter.contract;

public interface IConverter<K, V> {
	V convert(K from);
}