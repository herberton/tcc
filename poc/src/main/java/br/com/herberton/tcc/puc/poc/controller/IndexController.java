package br.com.herberton.tcc.puc.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;
import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Controller
public class IndexController {

	@Autowired
	private INetworkHelper networkHelper;

	@Autowired
	private ILoginBusiness loginBusiness;

	
	@RequestMapping({ "/", "/index" })
	public String index(@CookieValue(name="JSESSIONID", required=false) String jSessionId, Model model) {

		String networkAddress = networkHelper.getNetworkAddress();
		model.addAttribute("networkAddress", networkAddress);

		UserDTO user = loginBusiness.getLoggedUser(jSessionId);

		if (user == null) {
			return "login";
		}

		model.addAttribute("user", user);
		return "redirect:home";

	}

}