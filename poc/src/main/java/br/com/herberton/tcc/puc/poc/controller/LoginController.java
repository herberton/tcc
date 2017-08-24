package br.com.herberton.tcc.puc.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.LoginDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
public class LoginController {

	@Autowired
	private INetworkHelper networkHelper;
	
	@Autowired
	private ILoginBusiness loginBusiness;
	
	@RequestMapping("/login")
	public String login(LoginDTO dto, Model model) {
		
		String networkAddress = networkHelper.getNetworkAddress();
		model.addAttribute("networkAddress", networkAddress);
		
		boolean isOk = loginBusiness.login(dto);
		
		return isOk ? "home" : "forward:index";
		
	}
	

}