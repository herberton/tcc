package br.com.herberton.tcc.puc.poc.helper;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.helper.contract.IHttpServletHelper;

@Component
public class HttpServletHelper implements IHttpServletHelper {

	@Override
	public String getURI(HttpServletRequest httpServletRequest) {
		
		String uri = httpServletRequest.getRequestURI();
		
		int jSessionIdIndex = uri.indexOf(";jsessionid=");
		if (jSessionIdIndex > 0) {
			uri = uri.substring(0, jSessionIdIndex);
		}
		
		return uri;
	
	}
	
	@Override
	public String getJSessionId(HttpServletRequest httpServletRequest) {
		
		Cookie[] cookies = defaultIfNull(httpServletRequest.getCookies(), new Cookie[]{});
		
		List<Cookie> result = 
			stream(cookies)
				.filter(cookie -> cookie.getName().toUpperCase().equals("JSESSIONID"))
					.collect(toList());
		
		return result.size() > 0 ? result.get(0).getValue() : null;
		
	}
	
	@Override
	public String getJSessionId(HttpSessionEvent httpSessionEvent) {
		
		if(httpSessionEvent == null || httpSessionEvent.getSession() == null) {
			return null;
		}
		
		return httpSessionEvent.getSession().getId();
	
	}
	
}
