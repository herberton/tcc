package br.com.herberton.tcc.puc.poc.service.contract;

import br.com.herberton.tcc.puc.poc.dto.TicketDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListRoleDTO;
import br.com.herberton.tcc.puc.poc.dto.list.ListUserDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;

public interface IUserService {
	String PATH = "/user";
	TicketDTO save(UserDTO dto);
	ListUserDTO listUsers();
	ListRoleDTO listRoles();
	void delete(Integer id);
	UserDTO find(Integer id);
}