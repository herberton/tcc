package br.com.herberton.tcc.puc.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
public class IndexController {

	@Autowired
	private INetworkHelper networkHelper;
	
	
	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		
		String networkAddress = networkHelper.getNetworkAddress();
		model.addAttribute("networkAddress", networkAddress);
		
		return "login";
	
	}
	

}