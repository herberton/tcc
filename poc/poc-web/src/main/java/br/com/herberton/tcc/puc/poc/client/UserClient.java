package br.com.herberton.tcc.puc.poc.client;

import static br.com.herberton.tcc.puc.poc.helper.RESTClientHelper.url;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IUserService;

@Service
public class UserClient 
	implements IUserService {

	@Override
	public TicketDTO save(UserDTO dto) {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url(PATH), dto, TicketDTO.class);
	}

	@Override
	public ListUserDTO listUsers() {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH), ListUserDTO.class);
	}
	
	@Override
	public ListRoleDTO listRoles() {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH + "/roles"), ListRoleDTO.class);
	}

	@Override
	public void delete(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url(PATH, id));	
	}

	@Override
	public UserDTO find(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH, id), UserDTO.class);
	}
	
}