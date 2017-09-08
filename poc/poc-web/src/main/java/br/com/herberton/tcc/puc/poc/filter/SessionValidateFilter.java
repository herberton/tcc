package br.com.herberton.tcc.puc.poc.filter;

import static br.com.herberton.tcc.puc.poc.contract.IConstant.APP_PATH;
import static br.com.herberton.tcc.puc.poc.contract.IConstant.WEB_PATH;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;
import static org.apache.commons.lang3.StringUtils.endsWithAny;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.IHttpServletHelper;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;

@WebFilter
public class SessionValidateFilter implements Filter {
	
	private static final String[] IGNOREDS = 
		{
			".ico", ".gif", ".jpg", ".jpeg", ".png", ".css", ".js", ".jsp", ".svg", ".ttf", ".eot", ".woff", ".woff2",
			APP_PATH + "/", 
			WEB_PATH + "/", 
			WEB_PATH + "/index", 
			WEB_PATH + "/login", 
			WEB_PATH + "/registration/ecommerce-user/", 
			WEB_PATH + "/registration/ecommerce-user/form", 
			WEB_PATH + "/registration/ecommerce-user/form/save"
		};

	
	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private IHttpServletHelper httpServletHelper;
	
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
		String uri = httpServletHelper.getURI(httpServletRequest);
		
		if(endsWithAny(uri, IGNOREDS)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		
		String ticket = httpServletHelper.getCookieValue(httpServletRequest, TICKET_COOKIE_NAME);
		
		LoggedUserDTO user = this.authenticationService.getLoggedUser(ticket);
		if(user != null) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		
		HttpSession session = httpServletRequest.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		httpServletResponse.sendRedirect(WEB_PATH + "/login");
		
	}
	
	
	public void init(FilterConfig filterConfig) throws ServletException { }
	
	public void destroy() { }
	
}
