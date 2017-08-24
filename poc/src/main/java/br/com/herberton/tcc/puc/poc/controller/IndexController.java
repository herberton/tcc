package br.com.herberton.tcc.puc.poc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping({"/", "/index"})
  public String index(Model model) {
	  model.addAttribute("mensagem", "POC");
      return "login";
  }
  
}