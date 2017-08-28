package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.UserDTO;

public interface ILoginBusiness {
	String TICKET_COOKIE_NAME = "TICKET";
	String login(UserDTO user);	
	void removeLoggedUser(String ticket);
	UserDTO getLoggedUser(String ticket);
}