package br.com.herberton.tcc.puc.poc.service;

import static br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService.PATH;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.herberton.tcc.puc.poc.business.contract.IAuthenticationBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;

@RestController
@RequestMapping(PATH)
public class AuthenticationService implements IAuthenticationService {
	
	
	@Autowired
	private IAuthenticationBusiness authenticationBusiness;
	
	@Override
	@RequestMapping(path="/login", method = POST)
	public TicketDTO login(@RequestBody LoginDTO dto) {
		return this.authenticationBusiness.login(dto);
	}
	
	@Override
	@RequestMapping(path="/logout", method = PUT)
	@ResponseStatus(value = NO_CONTENT)
	public void logout(@RequestBody TicketDTO ticket) {
		this.authenticationBusiness.logout(ticket);
	}

	@Override
	@RequestMapping(path="/loggedUser/{ticket}", method = GET)
	public LoggedUserDTO getLoggedUser(@PathVariable String ticket) {
		return this.authenticationBusiness.getLoggedUser(ticket);
	}
	
}