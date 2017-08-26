package br.com.herberton.tcc.puc.poc.helper.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;

public interface IHttpServletHelper {
	
	String getURI(HttpServletRequest httpServletRequest);
	String getJSessionId(HttpServletRequest httpServletRequest);
	String getJSessionId(HttpSessionEvent httpSessionEvent);
	
}
