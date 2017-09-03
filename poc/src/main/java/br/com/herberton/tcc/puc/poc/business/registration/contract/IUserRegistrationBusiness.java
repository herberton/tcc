package br.com.herberton.tcc.puc.poc.business.registration.contract;

import java.util.List;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;

public interface IUserRegistrationBusiness {
	TicketDTO save(UserDTO dto);
	List<UserDTO> listUsers();
	List<RoleDTO> listRoles();
	void delete(Integer id);
	UserDTO find(Integer id);
}