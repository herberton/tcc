package br.com.herberton.tcc.puc.poc.service.contract;

import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;

public interface IAuthenticationService {
	String PATH = "/authentication";
	TicketDTO login(LoginDTO dto);	
	void logout(TicketDTO ticket);
	LoggedUserDTO getLoggedUser(String ticket);
}
