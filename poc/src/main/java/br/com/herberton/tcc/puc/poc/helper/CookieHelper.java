package br.com.herberton.tcc.puc.poc.helper;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.ICookieHelper;

@Component
public class CookieHelper implements ICookieHelper {

	@Override
	public Cookie newTicketCookie(TicketDTO dto) {
		return this.newTicketCookie(dto.getValue());
	}

	@Override
	public Cookie newTicketCookie(String ticket) {
		Cookie cookie = new Cookie(TICKET_COOKIE_NAME, ticket);
		cookie.setPath("/poc");
		return cookie;
	}

	
}
