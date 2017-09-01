package br.com.herberton.tcc.puc.poc.filter;

import static br.com.herberton.tcc.puc.poc.dto.TicketDTO.from;
import static br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper.TICKET_COOKIE_NAME;
import static org.apache.commons.lang3.StringUtils.endsWithAny;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.IHttpServletHelper;

public class SessionValidateFilter implements Filter {
	
	private static final String[] IGNOREDS = 
		{
			".ico", ".gif", ".jpg", ".jpeg", ".png", ".css", ".js", ".jsp", ".svg", ".ttf", ".eot", ".woff", 
			"/", "/index", 
			"/login", "/login.jsp", 
			"/registration/ecommerce-user", "/registration/ecommerce-user.jsp", "/registration/ecommerce-user/save"
		};

	
	@Autowired
	private IAuthenticationBusiness loginBusiness;
	
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
		
		LoggedUserDTO user = loginBusiness.getLoggedUser(from(ticket));
		if(user != null) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		
		HttpSession session = httpServletRequest.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		httpServletResponse.sendRedirect("/poc/login");
		
	}
	
	
	public void init(FilterConfig filterConfig) throws ServletException { }
	
	public void destroy() { }
	
}
