package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.UserDTO;

public interface ILoginBusiness {
	boolean login(String jSessionId, UserDTO user);	
	UserDTO getLoggedUser(String jSessionId);
	void removeLoggedUser(String jSessionId);
}
