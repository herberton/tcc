package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;

public interface IAuthenticationBusiness {
	TicketDTO login(LoginDTO dto);	
	void logout(TicketDTO ticket);
	LoggedUserDTO getLoggedUser(TicketDTO ticket);
}