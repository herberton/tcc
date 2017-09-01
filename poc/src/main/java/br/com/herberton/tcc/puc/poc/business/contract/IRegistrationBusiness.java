package br.com.herberton.tcc.puc.poc.business.contract;

import br.com.herberton.tcc.puc.poc.dto.EcommerceUserDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;

public interface IRegistrationBusiness {
	TicketDTO save(EcommerceUserDTO dto);
}