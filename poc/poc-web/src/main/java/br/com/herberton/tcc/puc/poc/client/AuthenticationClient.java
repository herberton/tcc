package br.com.herberton.tcc.puc.poc.client;

import static br.com.herberton.tcc.puc.poc.helper.RESTClientHelper.url;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.LoggedUserDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IAuthenticationService;

@Service
public class AuthenticationClient implements IAuthenticationService {

	@Override
	public TicketDTO login(LoginDTO dto) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(url(PATH + "/login"), dto, TicketDTO.class);
	}

	@Override
	public void logout(TicketDTO ticket) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url(PATH + "/logout"), ticket);
	}

	@Override
	public LoggedUserDTO getLoggedUser(String ticket) {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH + "/loggedUser", ticket), LoggedUserDTO.class);
	}
	
	
	
	
}