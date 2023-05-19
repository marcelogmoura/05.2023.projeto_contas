package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoriasConsultaController {
	
	@RequestMapping(value = "/admin/categorias-consulta")
	public ModelAndView categoriasConsulta() {
		ModelAndView modelAndView = new ModelAndView("admin/categorias-consulta");
		return modelAndView;
	}

}
