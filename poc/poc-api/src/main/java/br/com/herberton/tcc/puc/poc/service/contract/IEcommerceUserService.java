package br.com.herberton.tcc.puc.poc.service.contract;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListEcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;

public interface IEcommerceUserService {
	String PATH = "/ecommerce-user";
	TicketDTO save(EcommerceUserDTO dto);
	ListEcommerceUserDTO list();
}