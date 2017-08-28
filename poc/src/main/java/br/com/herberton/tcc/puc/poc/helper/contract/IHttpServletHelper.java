package br.com.herberton.tcc.puc.poc.helper.contract;

import javax.servlet.http.HttpServletRequest;

public interface IHttpServletHelper {
	String getURI(HttpServletRequest httpServletRequest);
	String getCookieValue(HttpServletRequest httpServletRequest, String cookieName);
}
