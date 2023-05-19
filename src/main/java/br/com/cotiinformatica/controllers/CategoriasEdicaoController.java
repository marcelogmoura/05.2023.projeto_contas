package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoriasEdicaoController {
	
	@RequestMapping(value = "/admin/categorias-edicao")
	public ModelAndView categoriasEdicao() {
		ModelAndView modelAndView = new ModelAndView("admin/categorias-edicao");
		return modelAndView;
	}

}
