package br.com.herberton.tcc.puc.poc.helper.contract;

import br.com.herberton.tcc.puc.poc.dto.RoleDTO;
import br.com.herberton.tcc.puc.poc.dto.user.UserDTO;

public interface IUserHelper {
	boolean contains(UserDTO user, RoleDTO role);
}