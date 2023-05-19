package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContasCadastroController {
	
	@RequestMapping(value = "/admin/contas-cadastro")
	public ModelAndView contasCadastro() {
		ModelAndView modelAndView = new ModelAndView("admin/contas-cadastro");
		return modelAndView;
	}

}
