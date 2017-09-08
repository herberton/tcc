package br.com.herberton.tcc.puc.poc.client;

import static br.com.herberton.tcc.puc.poc.helper.RESTClientHelper.url;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleTypeDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IRoleService;

@Service
public class RoleClient 
	implements IRoleService {
	
	@Override
	public void save(RoleDTO dto) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url(PATH), dto);
	}

	@Override
	public ListRoleDTO listRoles() {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH), ListRoleDTO.class);
	}

	@Override
	public ListRoleTypeDTO listRoleTypes() {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH + "/type"), ListRoleTypeDTO.class);
	}

	@Override
	public void delete(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url(PATH, id));	
	}

	@Override
	public RoleDTO find(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH, id), RoleDTO.class);
	}
	
}