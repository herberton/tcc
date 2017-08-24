package br.com.herberton.tcc.puc.poc.business;

import org.springframework.stereotype.Service;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;

@Service
public class LoginBusiness implements ILoginBusiness {
	
	@Override
	public boolean login(LoginDTO dto) {
		System.out.println(dto.getUser());
		System.out.println(dto.getPassword());
		return false;
	}
	
	
}
