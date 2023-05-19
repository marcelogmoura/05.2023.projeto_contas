package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoriasCadastroController {
	
	@RequestMapping(value = "/admin/categorias-cadastro")
	public ModelAndView categoriasCadastro() {
		ModelAndView modelAndView = new ModelAndView("admin/categorias-cadastro");
		return modelAndView;
	}

}
