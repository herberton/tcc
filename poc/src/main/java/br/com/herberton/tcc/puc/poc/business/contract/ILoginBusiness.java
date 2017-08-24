package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.LoginDTO;

public interface ILoginBusiness {
	
	boolean login(LoginDTO dto);
	
}
