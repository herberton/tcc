package br.com.herberton.tcc.puc.poc.business.registration.contract;

import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.EcommerceUserDTO;

public interface IEcommerceUserRegistrationBusiness {
	TicketDTO save(EcommerceUserDTO dto);
	List<EcommerceUserDTO> list();
}