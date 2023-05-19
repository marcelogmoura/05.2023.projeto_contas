package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContasConsultaController {
	
	@RequestMapping(value = "/admin/contas-consulta")
	public ModelAndView contasConsulta() {
		ModelAndView modelAndView = new ModelAndView("admin/contas-consulta");
		return modelAndView;
	}

}
