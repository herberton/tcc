package br.com.herberton.tcc.puc.poc.helper.contract;

import javax.servlet.http.Cookie;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;

public interface ICookieHelper {
	String TICKET_COOKIE_NAME = "TICKET";
	Cookie newTicketCookie(TicketDTO dto);
	Cookie newTicketCookie(String ticket);
}