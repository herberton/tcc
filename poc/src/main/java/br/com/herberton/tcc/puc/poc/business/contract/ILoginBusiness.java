package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;

public interface ILoginBusiness {
	String TICKET_COOKIE_NAME = "TICKET";
	String login(LoginDTO dto);	
	void logout(String ticket);
	LoggedUserDTO getLoggedUser(String ticket);
}