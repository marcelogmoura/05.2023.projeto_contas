package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.CategoriasEdicaoDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.TipoCategoria;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@Controller
public class CategoriasEdicaoController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@RequestMapping(value = "/admin/categorias-edicao")
	public ModelAndView categoriasEdicao(Integer idCategoria , HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/categorias-edicao");
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Categoria categoria = categoriaRepository.findById(idCategoria, usuario.getIdUsuario());
			
			CategoriasEdicaoDto dto = new CategoriasEdicaoDto();
			dto.setIdCategoria(categoria.getIdCategoria());
			dto.setNome(categoria.getNome());
			dto.setTipo(categoria.getTipo().toString());
			
			modelAndView.addObject("dto" , dto);
			modelAndView.addObject("tipos", TipoCategoria.values());
			
			modelAndView.addObject("mensagem" , "Editado com sucesso");
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem" , e.getMessage());
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/categorias-edicao-post" , method = RequestMethod.POST)
	public ModelAndView categoriasEdicaoPost(CategoriasEdicaoDto dto , HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("/admin/categorias-edicao");
		
		try {
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(dto.getIdCategoria());
			categoria.setNome(dto.getNome());
			categoria.setTipo(TipoCategoria.valueOf(dto.getTipo()));
			categoria.setIdUsuario(usuario.getIdUsuario());
			
			categoriaRepository.update(categoria);
			modelAndView.addObject("mensagem" , "Categoria atualizada");
			
			modelAndView.addObject("dto" , dto);
			modelAndView.addObject("tipo" , TipoCategoria.values());
			
		}catch (Exception e) {
			
			modelAndView.addObject("mensagem" , e.getMessage());
			
		}
		
		return modelAndView;
	}

}
