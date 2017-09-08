package br.com.herberton.tcc.puc.poc.service;

import static br.com.herberton.tcc.puc.poc.service.contract.IEcommerceUserService.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.herberton.tcc.puc.poc.business.contract.IEcommerceUserBusiness;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListEcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.service.contract.IEcommerceUserService;

@RestController
@RequestMapping(PATH)
public class EcommerceUserService implements IEcommerceUserService {
	
	@Autowired
	private IEcommerceUserBusiness ecommerceUserRegistrationBusiness;

	@Override
	@RequestMapping(method = POST)
	public TicketDTO save(@RequestBody EcommerceUserDTO dto) {
		return this.ecommerceUserRegistrationBusiness.save(dto);
	}

	@Override
	@RequestMapping(method = GET)
	public ListEcommerceUserDTO list() {
		return this.ecommerceUserRegistrationBusiness.list();
	}

	
	
}