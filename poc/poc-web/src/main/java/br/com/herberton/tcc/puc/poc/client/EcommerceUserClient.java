package br.com.herberton.tcc.puc.poc.client;

import static br.com.herberton.tcc.puc.poc.helper.RESTClientHelper.url;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListEcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IEcommerceUserService;

@Service
public class EcommerceUserClient 
	implements IEcommerceUserService {

	public TicketDTO save(EcommerceUserDTO dto) {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url(PATH), dto, TicketDTO.class);
	}

	public ListEcommerceUserDTO list() {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url(PATH), ListEcommerceUserDTO.class);
	}
	
	
	
	
}